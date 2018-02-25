import com.almundo.CallManager;
import com.almundo.domain.Client;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CallManagerTest {

    private static CallManager callManager;
    private static Faker faker = new Faker();

    @Before
    public void configCallManager() {
        callManager = new CallManager();
    }

    @Test
    public void testSingleCall() {
        Client client1 = new Client("1", faker.name().firstName(), faker.name().lastName(), "1111", callManager);
        assertThat(callManager.getDispatcher().getPendingCalls()).isEmpty();
    }

    @Test
    public void testTenCalls() {
        Client client1 = new Client("1", faker.name().firstName(), faker.name().lastName(), "1111", callManager);
        Client client2 = new Client("2", faker.name().firstName(), faker.name().lastName(), "2222", callManager);
        Client client3 = new Client("3", faker.name().firstName(), faker.name().lastName(), "3333", callManager);
        Client client4 = new Client("4", faker.name().firstName(), faker.name().lastName(), "4444", callManager);
        Client client5 = new Client("5", faker.name().firstName(), faker.name().lastName(), "5555", callManager);
        Client client6 = new Client("6", faker.name().firstName(), faker.name().lastName(), "6666", callManager);
        Client client7 = new Client("7", faker.name().firstName(), faker.name().lastName(), "7777", callManager);
        Client client8 = new Client("8", faker.name().firstName(), faker.name().lastName(), "8888", callManager);
        Client client9 = new Client("9", faker.name().firstName(), faker.name().lastName(), "9999", callManager);
        Client client10 = new Client("10", faker.name().firstName(), faker.name().lastName(), "10101", callManager);
        client1.makeCall();
        client2.makeCall();
        client3.makeCall();
        client4.makeCall();
        client5.makeCall();
        client6.makeCall();
        client7.makeCall();
        client8.makeCall();
        client9.makeCall();
        client10.makeCall();


        assertThat(callManager.getDispatcher().getPendingCalls()).isEmpty();
    }

}
