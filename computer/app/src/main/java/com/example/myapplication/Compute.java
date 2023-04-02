package com.example.myapplication;

public class Compute {
    String text;

    int index;
    float data;
    public float equaldata(String eq,String com){
        if(eq.equals("=")){
            //字符串
            for (int i=0;i<com.length();i++){
                //字符和字符串的判别相等的方法不同（字符串用equal,字符用==）
                char c=com.charAt(i);
                switch (c){
                    case '+':
                    case '-':
                    case '*':
                    case '÷':
                        index=i;
                }
            }
            //获取到运算符号前后的字符串
            String value1 = com.substring(0,index);
            //System.out.println(value1);
            String value2 = com.substring(index+1);
            //System.out.println(value2);
            //进行字符串转换为浮点数
            float num1 = Float.parseFloat(value1);
            float num2 = Float.parseFloat(value2);
            switch (com.charAt(index)){
                case '+':
                    data=num1+num2;
                    break;
                case '-':
                    data=num1-num2;
                    break;
                case '*':
                    data=num1*num2;
                    break;
                case '÷':
                    data=num1/num2;
                    break;
            }
        }else {

        }
        return data;
    }
}
