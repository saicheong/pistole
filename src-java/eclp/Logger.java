package eclp;

public class Logger {
    public void error(String msg){
        message("Error: " + msg);
    }
    public void error(String msg, Exception ex){
        error(msg);
        ex.printStackTrace();
    }
    public void warn(String msg){
        message("Warn: " + msg);
    }
    public void info(String msg){
        message("Info: " + msg);
    }

    private void message(String msg){
        System.out.println(msg);
    }

}
