import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

class AmazonSESFacade {

    void sendMail(Mail mail) {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("{AWS_ACCESS_KEY_ID}", "{AWS_SECRET_ACCESS_KEY}");

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                            .withRegion(Regions.US_WEST_2).build();

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(mail.getToList()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(mail.getHtmlBody()))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(mail.getTextBody())))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(mail.getSubject())))
                    .withSource(mail.getFrom());
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}
