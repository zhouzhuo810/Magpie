package me.zhouzhuo810.magpie.utils;

import android.widget.EditText;

/**
 * EditText操作相关
 */
public class EditUtil {


    /**
     * 获取EditText光标所在行的内容（不包括换行符）
     *
     * @param et EditText
     * @return 内容CharSequence
     */
    public static CharSequence getEditTextCursorLineContent(EditText et) {
        final String content = et.getText().toString();
        if (content.length() == 0) {
            return content;
        }
        //获取行头下标
        int lineHeadIndex = getEditTextCursorLineFirstIndex(et);
        //获取行尾下标
        int lineEndIndex = getEditTextCursorLineLastIndex(et);
        //获取当前行内容
        return content.substring(lineHeadIndex, lineEndIndex + 1);
    }

    /**
     * 获取EditText光标所在行的内容（不包括换行符）
     *
     * @param et EditText
     * @return 内容String
     */
    public static String getEditTextCursorLineContentString(EditText et) {
        final String content = et.getText().toString();
        if (content.length() == 0) {
            return content;
        }
        //获取行头下标
        int lineHeadIndex = getEditTextCursorLineFirstIndex(et);
        //获取行尾下标
        int lineEndIndex = getEditTextCursorLineLastIndex(et);
        //获取当前行内容
        return content.substring(lineHeadIndex, lineEndIndex + 1);
    }

    /**
     * 获取EditText光标所在行的行首的位置
     *
     * @param et EditText
     * @return 内容
     */
    public static int getEditTextCursorLineFirstIndex(EditText et) {
        final String content = et.getText().toString();
        int index = et.getSelectionStart();
        if (index > 0) {
            for (int i = index - 1; i >= 0; i--) {
                if (content.length() > i) {
                    if (content.charAt(i) == '\n') {
                        return i + 1;
                    } else if (i == 0) {
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 获取EditText光标所在行的行尾的位置
     *
     * @param et EditText
     * @return 内容
     */
    public static int getEditTextCursorLineLastIndex(EditText et) {
        final String content = et.getText().toString();
        int index = et.getSelectionStart();
        if (index >= 0) {
            for (int i = index; i < content.length(); i++) {
                if (content.charAt(i) == '\n') {
                    return i - 1;
                } else if (i == content.length() - 1) {
                    return i;
                }
            }
            return index - 1;
        } else {
            return 0;
        }
    }

}
