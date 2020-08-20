package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

public class SoNguyen {
    private String mathExpression;


    public SoNguyen(){
    }

    public String getMathExpression() {
        return mathExpression;
    }

    public void setMathExpression(String mathExpression) {
        this.mathExpression = mathExpression;
    }


    public BigInteger add(BigInteger operand1, BigInteger operand2){
        return operand1.add(operand2);
    }

    public BigInteger subtract(BigInteger operand1,BigInteger operand2){
        return operand1.subtract(operand2);
    }

    public BigInteger multiply(BigInteger operand1,BigInteger operand2){
        return operand1.multiply(operand2);
    }

    public BigInteger div(BigInteger operand1,BigInteger operand2){
        return operand1.divide(operand2);
    }

    public BigInteger mod(BigInteger operand1,BigInteger operand2){
        return operand1.mod(operand2);
    }

    public BigInteger luyThua(BigInteger operand1,BigInteger operand2){
        return operand1.pow(Integer.valueOf(String.valueOf(operand2)));
    }

     public BigInteger factorial(BigInteger n) {
        BigInteger result = BigInteger.ONE;
        long k =n.longValue();
        long factor = 1;
        for (long i = k; i > 1; --i) {
            if (Long.MAX_VALUE / factor < i) {
                result = result.multiply(BigInteger.valueOf(factor));
                factor = i;
            } else {
                factor *= i;
            }
        }
        return result.multiply(BigInteger.valueOf(factor));
    }

    public int precedence(char c){
        if(c=='('||c==')') return 0;
        else if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/' || c=='%') return 2;
        else if(c == '^') return 3;
        else if(c == '!') return 4;
        else return -1;
    }


    public boolean isOperator(char c){
        char operator[] = { '+', '-', '*', '/', ')', '(','^','!','%' };
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1)
            return true;
        else return false;
    }

    public String[] processString(String sMath){
        String s1 = "", elementMath[] = null;
        sMath = sMath.trim();
        sMath = sMath.replaceAll("\\s+"," "); //    chuan hoa sMath
        for (int i=0; i<sMath.length(); i++){
            char c = sMath.charAt(i);//sMath.substring(i,1);
            if (!this.isOperator(c))
                s1 = s1 + c;
            else s1 = s1 + " " + c + " ";
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+"," "); //  chuan hoa s1
        elementMath = s1.split(" "); //tach s1 thanh cac phan tu

        return elementMath;
    }

    public String[] postfix(String[] elementMath){
        String s1 = "", E[];
        Stack <String> S = new Stack <String>();
        for (int i=0; i<elementMath.length; i++){    // duyet cac phan tu
            char c = elementMath[i].charAt(0);  // c la ky tu dau tien cua moi phan tu

            if (!this.isOperator(c))         // neu c khong la toan tu
                s1 = s1 + " " + elementMath[i];     // xuat elem vao s1
            else{                       // c la toan tu
                if (c == '(') S.push(elementMath[i]);   // c la "(" -> day phan tu vao Stack
                else{
                    if (c == ')'){          // c la ")"
                        char c1;        //duyet lai cac phan tu trong Stack
                        do{
                            c1 = S.peek().charAt(0);    // c1 la ky tu dau tien cua phan tu
                            if (c1 != '(') s1 = s1 + " " + S.peek();    // trong khi c1 != "("
                            S.pop();
                        }while (c1 != '(');
                    }
                    else{
                        while (!S.isEmpty() && this.precedence(S.peek().charAt(0)) >= this.precedence(c)){
                            // Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
                            s1 = s1 + " " + S.peek();   // xuat phan tu trong Stack ra s1
                            S.pop();
                        }
                        S.push(elementMath[i]); //  dua phan tu hien tai vao Stack
                    }
                }
            }
        }
        while (!S.isEmpty()){   // Neu Stack con phan tu thi day het vao s1
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        E = s1.split(" ");  //  tach s1 thanh cac phan tu
        return E;
    }

    public String valueMath(String[] elementMath){
        Stack <String> ketQua = new Stack<String>();
        BigInteger num=new BigInteger("0");
        for (int i=1; i<elementMath.length; i++){
            char c = elementMath[i].charAt(0);
            if (!this.isOperator(c)) ketQua.push(elementMath[i]);
            else {
                if(precedence(c)>=1&&precedence(c)<=3){
                    if(ketQua.size()<2){
                        String s="Lỗi biểu thức 1!";
                        return s;
                    }
                    try {
                        BigInteger y = new BigInteger(ketQua.pop());
                        BigInteger x = new BigInteger(ketQua.pop());
                        
                        switch (c) {
                            case '+':
                                num=this.add(x, y);
                                break;
                            case '-':
                                num=this.subtract(x, y);
                                break;
                            case '*':
                                num=this.multiply(x, y);
                                break;
                            case '/':
                                try {
                                    num=this.div(x, y);
                                }
                                catch (Exception exception) {
                                    String s="Lỗi chia 0!";
                                    return s;
                                }
                                break;
                            case '%':
                                try {
                                    num=this.mod(x, y);
                                }
                                catch (Exception exception) {
                                    String s="Lỗi chia 0!";
                                    return s;
                                }
                                break;
                            case '^':
                                num=this.luyThua(x,y);
                                break;
//                            case '!':
//                                num=this.factorial(y);
//                                break;
                            default:
                                break;
                            
                        }
                    } catch(Exception exception) {
                        String s="Lỗi biểu thức 2!";
                        return s;
                    }
                }
                else if(precedence(c)==4){
                    if(ketQua.size()<1){
                        String s="Lỗi biểu thức 4!";
                        return s;
                    }
                    else{
                        BigInteger m= new BigInteger(ketQua.pop());
                        if(BigInteger.valueOf(0).compareTo(m)>0){
                            String s="Lỗi biểu thức 6!";
                            return s;
                        }
                        else num=this.factorial(m);
                    }
                }
                else{
                    String s="Lỗi biểu thức 3!";
                    return s;
                }
                ketQua.push(String.valueOf(num));
                }
            }
        if(ketQua.size()==1)
            return ketQua.pop();
        else {
            String s="Lỗi biểu thức 5!";
            return s;
        }
    }
}
