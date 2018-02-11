package constant;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MyDocument extends PlainDocument{//限制输入长度
        private int maxLength;  
        
        public MyDocument(int newMaxLength){  
            super();  
            maxLength = newMaxLength;  
        }  
        public void insertString(int offset,String str,javax.swing.text.AttributeSet a) throws BadLocationException{  
            if(getLength()+str.length()>maxLength){  
                return;  
            }else{  
                super.insertString(offset, str,a);  
            }  
        }
}