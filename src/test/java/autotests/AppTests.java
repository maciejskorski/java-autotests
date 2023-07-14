package autotester;

import org.junit.jupiter.api.Test;

import autotester.App;

public class AppTests {

    @Test
    public void testApp() {
        String[] args = {"arg1","arg2","arg3"};
        App instance = new App();
        instance.main(args);
    }

}