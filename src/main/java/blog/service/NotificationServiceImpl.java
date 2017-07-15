package blog.service;

import blog.model.NotificationMessage;
import blog.model.NotificationMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service()
public class NotificationServiceImpl implements NotificationService {
    public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

    @Autowired
    private HttpSession httpSession;

    @Override
    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.INFO, msg);
    }

    private void addNotificationMessage(NotificationMessageType info, String msg) {
        List<NotificationMessage> notificationMessages =
                (List<NotificationMessage>) httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);

        if (notificationMessages == null){
            notificationMessages = new ArrayList<NotificationMessage>();
        }
        notificationMessages.add(new NotificationMessage(info, msg));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notificationMessages);

    }

    @Override
    public void addErrorMessage(String msg) {
        addNotificationMessage(NotificationMessageType.ERROR, msg);
    }
}
