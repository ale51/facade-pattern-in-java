import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
class Mail {

    private String from;

    private List<String> toList;

    private String subject;

    private String htmlBody;

    private String textBody;

}
