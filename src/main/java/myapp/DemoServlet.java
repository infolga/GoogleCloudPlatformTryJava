/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myapp;

import refactoring.Math.MathElement;
import refactoring.Math.MyException;
import refactoring.Math.Parser;
import refactoring.MathOperations.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {
    Parser parser = new Parser();

    public DemoServlet() {


        parser.addMapOp("cos(", new Cos(0, 0));
        parser.addMapOp("/2", new Divide(0, 0));
        parser.addMapOp("%2", new Mod(0, 0));
        parser.addMapOp("-1", new Negative(0, 0));
        parser.addMapOp("+1", new Positive(0, 0));
        parser.addMapOp("sin(", new Sin(0, 0));
        parser.addMapOp("sqrt(", new Sqrt(0, 0));
        parser.addMapOp("-2", new Subtraction(0, 0));
        parser.addMapOp("+2", new Sum(0, 0));
        parser.addMapOp("^2", new Pow(0, 0));
        parser.addMapOp("*2", new Multiplication(0, 0));
        parser.addMapOp("abs(", new Abs(0, 0));
        parser.addMapOp("deg(", new ToDeg(0, 0));
        parser.addMapOp("rad(", new ToRad(0, 0));

    }



    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("text/plain");
        try {
            String str = req.getParameter("str");
            MathElement calculate;
            parser.setStr(str);
            calculate = parser.calculate();
            double v = calculate.calculate();
            String format = String.format("%.3f", v);
            resp.getWriter().println("{\"solve\": \"" + format + "\" }");
        } catch (MyException e1) {
            String s= "{\"error\": \"" + e1.message + "\"}";
            resp.getOutputStream().write(s.getBytes("UTF-8"));
        }
    }
}
