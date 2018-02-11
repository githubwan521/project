package ui;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//限制输入框只能输入小数 
public class DoubleDocument extends PlainDocument {  
    private static final long serialVersionUID = 1L;  
    private String reg = "^[0-9]+([.]{1}[0-9]+){0,1}$";  
    public void insertString(int offset,String s,AttributeSet attributeSet) throws BadLocationException {  
        if (offset == 0 && s.equals(".")) {  
            Toolkit.getDefaultToolkit().beep();  
            return;
        }  
        String str = this.getText(0, this.getLength()) + s;  
        int i = this.getText(0, this.getLength()).indexOf(".");  
        if (i == -1 && str.endsWith(".")) {  
            super.insertString(offset, s, attributeSet);  
            return;  
        }  
        if (str.matches(reg)) {  
            super.insertString(offset, s, attributeSet);  
        } else {  
            Toolkit.getDefaultToolkit().beep();  
        }  
    }  
}  
  