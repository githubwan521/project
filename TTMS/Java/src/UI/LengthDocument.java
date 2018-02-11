package ui;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
//������������������볤��
public class LengthDocument extends PlainDocument{
        private int maxLength;  
        
        public LengthDocument(int newMaxLength){  
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