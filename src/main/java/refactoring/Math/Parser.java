package refactoring.Math;


import java.lang.reflect.Constructor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public void setCalculateImmediately(boolean calculateImmediately) {
        isCalculateImmediately = calculateImmediately;
    }

    private boolean isCalculateImmediately = true;
    private String str;
    private Stack<MathElement> stack;
    private LinkedList<MathElement> rezult;
    private Map<String, MathElement> mapOp;

    private Pattern pattern;
    private Matcher matcher;

    private final String reg;
    private final String znackBinar = "(?<znackBinar>(?<=(?:[0-9]|\\)))[^\\w\\d() ](?=(?:[^\\w\\d() ]|\\(|[a-z0-9])))";
    private final String znackUnar = "(?<znackUnar>(?<=(?:\\A|\\(|[^\\w\\d() ]))[^\\w\\d() ](?=(?:[0-9a-z]|\\()))";
    private final String number = "(?<number>(?<=(?:[^\\w\\d() ]|\\A|\\())[0-9]+\\.?[0-9]*(?=(?:[^\\w\\d() ]|\\)|$)))";
    private final String opBracket = "(?<opBracket>(?<=(?:\\A|[^\\w\\d() ]|\\())\\()";
    private final String clBracket = "(?<clBracket>\\))";
    private final String function = "(?<function>(?<=(?:[^\\w\\d() ]|\\(|\\A))[a-z]+\\((?=(?:[^)])))";

    public Parser(String str) throws MyException {
        this();
        this.str = preprocessing(str);
    }

    public Parser() {
        this.mapOp = new HashMap<String, MathElement>();

        reg = znackBinar + "|" + znackUnar + "|" + number + "|" + opBracket + "|" + clBracket + "|" + function;
        pattern = Pattern.compile(reg);
        this.stack = new Stack<MathElement>();
        this.rezult = new LinkedList<MathElement>();
    }

    public MathElement calculate() throws MyException {
        if (str != null) {

            parse();

            return createMathOperation();
        } else {
            return null;
        }
    }

    private MathElement createMathOperation() throws MyException {

        if (rezult.size() < 1) {
            throw new MyException("Слишком маленькое выражение", str, 0, str.length());
        }
        ListIterator<MathElement> mathElementListIterator = rezult.listIterator();
        while (rezult.size() > 1) {
            MathElement A = mathElementListIterator.next();
            if (A.isEmpty()) {
                if (A instanceof UnarFunction) {
                    UnarFunction buf = (UnarFunction) A;
                    mathElementListIterator.remove();
                    if (isCalculateImmediately) {
                        buf.setA(new Number(mathElementListIterator.previous().calculate(),A.indexStart,A.indexEnd));
                    } else {
                        buf.setA(mathElementListIterator.previous());
                    }
                    mathElementListIterator.set(buf);
                    mathElementListIterator.next();
                } else if (A instanceof UnarOperation) {
                    UnarOperation buf = (UnarOperation) A;
                    mathElementListIterator.remove();
                    if (isCalculateImmediately) {
                        buf.setA(new Number(mathElementListIterator.previous().calculate(),A.indexStart,A.indexEnd));
                    } else {
                        buf.setA(mathElementListIterator.previous());
                    }
                    mathElementListIterator.set(buf);
                    mathElementListIterator.next();
                } else if (A instanceof BinarOperation) {
                    BinarOperation buf = (BinarOperation) A;
                    mathElementListIterator.remove();
                    if (isCalculateImmediately) {
                        buf.setB(new Number(mathElementListIterator.previous().calculate(),A.indexStart,A.indexEnd));
                    } else {
                        buf.setB(mathElementListIterator.previous());
                    }
                    mathElementListIterator.remove();
                    if (isCalculateImmediately) {
                        buf.setA(new Number(mathElementListIterator.previous().calculate(),A.indexStart,A.indexEnd));
                    } else {
                        buf.setA(mathElementListIterator.previous());
                    }
                    mathElementListIterator.set(buf);
                    mathElementListIterator.next();
                }
            }
        }
        return rezult.getFirst();
    }

    //производим перевод математической записи в постпрефексную
    public void parse() throws MyException {
        if (str == null) {
            throw new MyException("Пустая строка", "", 0, 0);
        }
        matcher = pattern.matcher(str);
        stack.clear();
        rezult.clear();
        while (matcher.find()) {
            String group = matcher.group();

            if (group.equals(matcher.group("znackBinar"))) {
                try {
                    stackAdd(createClassByStr(group + "2", matcher.start(), matcher.end()));
                } catch (MyException e) {
                    e.message = "Неизвестный знак";
                    throw e;
                }
            } else if (group.equals(matcher.group("znackUnar"))) {
                try {
                    stackAdd(createClassByStr(group + "1", matcher.start(), matcher.end()));
                } catch (MyException e) {
                    e.message = "Неизвестный знак";
                    throw e;
                }
            } else if (group.equals(matcher.group("number"))) {
                rezult.add(new Number(group, matcher.start(), matcher.end()));
            } else if (group.equals(matcher.group("opBracket"))) {
                stackAdd(new OpBracket(matcher.start(), matcher.end()));
            } else if (group.equals(matcher.group("clBracket"))) {
                transferStack();
            } else if (group.equals(matcher.group("function"))) {
                try {
                    stackAdd(createClassByStr(group, matcher.start(), matcher.end()));
                } catch (MyException e) {
                    e.message = "Неизвестная функция";
                    throw e;
                }
            }
        }
        resetStack();
    }

    private MathElement createClassByStr(String group, int indexStart, int indexEnd) throws MyException {
        MathElement element = mapOp.get(group);
        if (element != null) {
            return element.getInstance(indexStart, indexEnd);
        } else {
            throw new MyException("", str, matcher.start(), matcher.end());
        }
    }

    private void resetStack() throws MyException {
        while (!stack.empty()) {
            if (stack.peek() instanceof OpBracket) {
                OpBracket opBracket = (OpBracket) stack.pop();
                throw new MyException("Нехватает закрывающейся скобки", str, opBracket.getIndexStart(), opBracket.getIndexEnd());
            }
            if (stack.peek() instanceof Function) {
                Function function = (Function) stack.pop();
                throw new MyException("Нехватает закрывающейся скобки для функции", str, function.getIndexStart(), function.getIndexEnd());
            }
            rezult.add(stack.pop());
        }
    }

    private void stackAdd(MathElement A) {
        if (A instanceof Function) {
            stack.push(A);
        } else if (A instanceof OpBracket) {
            stack.push(A);
        } else if (stack.empty() || stack.peek() instanceof OpBracket || stack.peek() instanceof Function) {
            stack.push(A);
        } else if (((Operation) stack.peek()).compareTo((Operation) A) < 0) {
            stack.push(A);
        } else {
            transferStack(A);
            stack.push(A);
        }
    }

    private void transferStack(MathElement A) {
        while (!stack.empty()) {
            if (stack.peek() instanceof Function) {
                return;
            } else if (stack.peek() instanceof OpBracket) {
                return;
            } else if (((Operation) stack.peek()).compareTo((Operation) A) < 0) {
                return;
            } else {
                rezult.add(stack.pop());
            }
        }
    }

    private void transferStack() throws MyException {
        while (true) {
            if (stack.empty()) {
                throw new MyException("Нехватает открывающейся скобки ", str, matcher.start(), matcher.end());
            } else if (stack.peek() instanceof Function) {
                rezult.add(stack.pop());
                return;
            } else if (stack.peek() instanceof OpBracket) {
                stack.pop();
                return;
            } else {
                rezult.add(stack.pop());
            }
        }
    }

    private String preprocessing(String st) throws MyException {
        String b = st.toLowerCase();

        Pattern pat = Pattern.compile("[^\\^a-z0-9\\(\\)\\+-/% \\*]+");//шаблон для проверки на разрешенные символы
        Matcher match = pat.matcher(b);

        if (match.find()) {
            throw new MyException("Недопустимые символы в строке  ", b, match.start(), match.end());
        }


        b = b.trim();
        b = b.replaceAll("[\\n\\t\\r]", " ");//замена спец символов
        b = b.replaceAll("\\s{2,}", " ");//уменьшаем количество пробелов


        //убираем лишние пробелы
        // пробелы не убираются между цифрами, буквами
        // между цифрой и буквой и наоборот
        b = b.replaceAll("(?<=\\d|\\))\\s+(?=(\\^|%|\\+|\\*|-|/)|\\))", "");
        b = b.replaceAll("(?<=(\\^|%|\\+|\\*|-|/)|\\()\\s+(?=((\\^|%|\\+|\\*|-|/)|\\d|\\w|\\(|\\)))", "");
        b = b.replaceAll("(?<=(\\^|%|\\+|\\*|-|/)|\\w)\\s+(?=(\\())", "");

        String buf = b.replaceAll(" ", "");

        match = pattern.matcher(b);
        int i = 0;
        //в цикле мы пытаемся найти недопустимые символы и их расположение
        while (match.find()) {

            String group = match.group();
            String B = buf.substring(i, i + group.length());

            if (!B.equals(group) || i != match.start()) {
                throw new MyException("Parser недопустимые символы или функции в строке  ", b, i, match.start());
            }
            i = i + group.length();
        }
        if (i != buf.length()) {
            throw new MyException("Parser недопустимые символы или функции в строке  ", b, i, b.length());
        }
        return b;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (MathElement element : rezult) {
            buf.append(element.toString());
            buf.append(" ");
        }
        return buf.toString();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) throws MyException {
        this.str = preprocessing(str);
    }

    public void addMapOp(String chars, MathElement mathElement) {
        this.mapOp.put(chars, mathElement);
    }
}
