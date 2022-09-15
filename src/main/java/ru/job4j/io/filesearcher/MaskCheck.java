package ru.job4j.io.filesearcher;

public class MaskCheck {
    private static boolean maskCheck(String file, String fileMask) {
        char[] fileArray = file.toCharArray();
        char[] maskArray = fileMask.toCharArray();
        int fileCounter = 0;
        int maskCounter = 0;
        int starIndex = -1;
        boolean result = false;
        while (fileCounter < fileArray.length && maskCounter < maskArray.length) {
            if (maskArray[maskCounter] == '*') {
                starIndex = maskCounter;
                maskCounter++;
                if (maskCounter == maskArray.length) {
                    fileCounter = fileArray.length;
                }
            } else if (maskArray[maskCounter] == '?' || maskArray[maskCounter] == fileArray[fileCounter]) {
                result = true;
                maskCounter++;
                fileCounter++;
            } else if (maskArray[maskCounter] != fileArray[fileCounter]) {
                if (starIndex != -1 && result) {
                    maskCounter = starIndex + 1;
                } else if (starIndex == -1) {
                    result = false;
                    break;
                } else {
                    fileCounter++;
                }
                result = false;
            }
        }
        return result && (fileCounter == fileArray.length && maskCounter == maskArray.length);
    }
}
