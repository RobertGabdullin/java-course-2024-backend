import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.processor.UserMessageProcessorImpl;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyBotTest {

    @Test
    public void testProcess() {
        Update updateMock = mock(Update.class);
        Message messageMock = mock(Message.class);
        Chat chatMock = mock(Chat.class);
        when(updateMock.message()).thenReturn(messageMock);
        when(messageMock.chat()).thenReturn(chatMock);
        when(chatMock.id()).thenReturn(1L);
        when(messageMock.text()).thenReturn("/unknown");
        UserMessageProcessorImpl messageProcessor = new UserMessageProcessorImpl();
        assertThat(messageProcessor.process(updateMock)
            .equals(new SendMessage(1L, "Unknown command. Type /help for available commands.")));
    }
}
