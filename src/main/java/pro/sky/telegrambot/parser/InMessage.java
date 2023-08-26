package pro.sky.telegrambot.parser;

public class InMessage {
    public static String parseInMessage(String s){
        String[] arr = s.split("\\s");

        if(arr.length<2){
            return null;
        }
        StringBuilder preparedMessageText = new StringBuilder(arr[2]);
        for(int i=3;i< arr.length;i++){
            preparedMessageText.append(" " + arr[i]);
        }

        return preparedMessageText.toString();
    }



}
