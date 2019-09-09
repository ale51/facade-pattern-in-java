import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Mail mail = Mail.builder()
                .toList(Arrays.asList("foo@example.com"))
                .from("bar@example.com")
                .subject("test")
                .htmlBody("hello")
                .textBody("world")
                .build()
                ;

        AmazonSESFacade amazonSESFacade = new AmazonSESFacade();
        amazonSESFacade.sendMail(mail);
    }
}
