package chth.playground.front.controller;

import com.chth.string.RegularStringUtil;
import com.chth.util.CharacterType;

import java.util.ArrayList;

/**
 * @author: 程泰恒
 * @date: 2019/7/19 8:57
 */

public class Test {

    public static void main(String[] args) {
        String regexByCharacterTypes = RegularStringUtil.getRegexByCharacterTypes(CharacterType.中文, CharacterType.大写字母, CharacterType.大小写字母);

        System.out.println(regexByCharacterTypes);

        String string = RegularStringUtil.getStringByCharacterTypes("nihao是的",CharacterType.中文);

        System.out.println(string);

    }
}
