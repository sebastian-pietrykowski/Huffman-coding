package pl.edu.pw.ee.files_manipulation;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameManipulator {

    public String removeExtension(String fileName) {
        validateString(fileName);
        return fileName.replaceFirst("[.][^.]+$", "");
    }

    public String getExtension(String fileName) {
        validateString(fileName);
        Pattern pattern = Pattern.compile("[.][^.]+$");
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();
        return matcher.group();
    }

    public boolean endsWith(String fileName, String postfix) {
        validateString(fileName);
        validateString(postfix);
        return fileName.endsWith(postfix);
    }

    public String removePostfix(String fileName, String postfix) {
        validateString(fileName);
        validateString(postfix);
        if (!fileName.endsWith(postfix)) {
            throw new IllegalArgumentException(
                    "Given file doesn't end with such a postfix.");
        }

        int postfixLen = postfix.length();
        int fileNameLen = fileName.length();

        return fileName.substring(0, fileNameLen - postfixLen);
    }

    public File removePostfixBeforeExtension(File file, String postfix) {
        new FileValidator(file).validateIfFileIsNotNull();
        validateString(postfix);

        String fileName = file.getName();
        String extension = getExtension(fileName);
        fileName = removeExtension(fileName);

        if (!fileName.endsWith(postfix)) {
            throw new IllegalArgumentException(
                    "Given file doesn't end with such a postfix.");
        }

        String fileNameNoPostfix = removePostfix(fileName, postfix);
        String parentPathWithSlash = getParentPathWithSlash(file);
        String resultFileName = parentPathWithSlash + fileNameNoPostfix
                + extension;

        return new File(resultFileName);
    }

    public File addPostfixBeforeExtension(File file, String postfix) {

        new FileValidator(file).validateIfFileIsNotNull();
        validateString(postfix);

        String fileName = file.getName();
        String extension = getExtension(fileName);
        String fleNameNoExt = removeExtension(fileName);
        String parentPathWithSlash = getParentPathWithSlash(file);

        String resultFileName = parentPathWithSlash + fleNameNoExt
                + postfix + extension;
        File resultFile = new File(resultFileName);

        return resultFile;
    }

    private String getParentPathWithSlash(File file) {
        new FileValidator(file).validateIfFileIsNotNull();
        String parentPath = file.getParentFile() == null
                ? "" : file.getParentFile().getAbsolutePath();
        return "".equals(parentPath) ? "" : parentPath + "/";

    }

    private void validateString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String argument is null");
        }
    }
}
