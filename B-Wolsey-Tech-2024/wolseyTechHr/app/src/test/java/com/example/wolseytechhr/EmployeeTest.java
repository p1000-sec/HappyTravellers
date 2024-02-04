package com.example.wolseytechhr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmployeeTest {
    @Test
    public void testMakeURLToGetAuthCode() {
        String[] userCredentials = {"thefoursuits@wolsey-tech.com", "Welcome!", "test"};
      //  Employee test = new Employee(userCredentials[0], userCredentials[1], userCredentials[2]);

   //     assertEquals(test.getLinkToGetAuthCode(), "https://hr-demo.wolsey-tech.com/get_auth_code.asp?user_name=thefoursuits@wolsey-tech.com&password=Welcome!&company_name=test");
    }

    @Test
    public void testFindAuthCode() {
        String[] userCredentials = {"thefoursuits@wolsey-tech.com", "Welcome!", "test"};
     //   Employee test = new Employee(userCredentials[0], userCredentials[1], userCredentials[2]);

  //      assertEquals(test.getAuth_code(), "30XONYJKS0RX2IQYGBUCDKGSB");
    }
    @Test
    public void testFindUserProfileDataLink() {
        String[] userCredentials = {"thefoursuits@wolsey-tech.com", "Welcome!", "test"};
      //  Employee test = new Employee(userCredentials[0], userCredentials[1], userCredentials[2]);

      //  assertEquals(test.getLinkToGetProfileInfo(), "https://hr-demo.wolsey-tech.com/get_data.asp?auth_code=30XONYJKS0RX2IQYGBUCDKGSB&query_type=personal_info");
    }
    @Test
    public void testGetRawProfileInfo() {
        String[] userCredentials = {"thefoursuits@wolsey-tech.com", "Welcome!", "test"};
     //   Employee test = new Employee(userCredentials[0], userCredentials[1], userCredentials[2]);

      //  assertEquals("[first_name=Four],[middle_name=],[last_name=Suits],[address_1=Augustana Campus],[address_2=],[city=Camrose],[province_name=],[postal_code=],[home_phone=],[cell_phone=780-608-1971],[email=thefoursuits@wolsey-tech.com]", test.getRawProfileInfo());
    }
    @Test
    public void testName() {
        String[] userCredentials = {"thefoursuits@wolsey-tech.com", "Welcome!", "test"};
    //    Employee test = new Employee(userCredentials[0], userCredentials[1], userCredentials[2]);

   //     assertEquals("Four", test.getFirstName());
    }

    @Test
    public void testMiddleName() {
        String[] userCredentials = {"thefoursuits@wolsey-tech.com", "Welcome!", "test"};
    //    Employee test = new Employee(userCredentials[0], userCredentials[1], userCredentials[2]);

   //     assertEquals(null, test.getMiddleName());
    }
}
