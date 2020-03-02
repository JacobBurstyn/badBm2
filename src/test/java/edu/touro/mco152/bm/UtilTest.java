package edu.touro.mco152.bm;

import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {


    /**
     * This test uses the "c" of BICEP in this test. It cross checs by trying
     * to create a new file in a directory that the deleteDirectory
     * method was supposed to delete. If the method had failed and
     * not deleted the directory then this this test would fail
     * because it would be successfull in creating a file in
     * the specified directory which would return true
     * <p>
     *  I think this also shows the "E" because it shows that
     *  the correct exception was caught, as the try catch shows -
     *  since there is no dir there it throws an exception when it
     *  tries to create a file in the dir (I try catch it to be able to
     *  run the assertEquals and see wheter it passes or fails)
     * @throws IOException
     */
    @Test
    void testDeleteDirectory() throws IOException {
        boolean b = true;
        File dirToDelete = new File("C:\\Users\\yaakov\\test");
        dirToDelete.mkdir();
        File file = new File ("C:\\Users\\yaakov\\test\\.test.txt");
        Util.deleteDirectory(dirToDelete);
        try {
            file.createNewFile();
        }catch (IOException e){
            b = false;
        }
        assertEquals(false,b);
    }



    /**
     * This method uses the paramatizedTest.
     * It checks to make sure that the method is indeed working
     * and returning the correct model based on the drive it is given
     * @param letter
     * @param expected
     */
    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "C,HGST HTS541010A7E630",
            "E,SD Card",

    })
    void testGetModelFromLetter2(String letter, String expected){
        String x = Util.getModelFromLetter2("E");
        assertEquals(expected.trim(),Util.getModelFromLetter2(letter).trim(),
                () -> letter + " should equal " + expected);
    }

    /**
     * This test, tests randInt() to ensure the boundry.
     * The test assures that the random int that is generated
     * is never out of the bounds set by max and min
     * <p>
     * Broke this by by switching the +1 to + 100
     */
    @Test
    void testRandInt(){
        int max = 10;
        int min = 5;
        int x = Util.randInt(5,10);
        assertEquals(true,x <= 10 && x >= 5,  "num is " + x);
    }
}