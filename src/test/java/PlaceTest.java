import org.junit.*;
import static org.junit.Assert.*;

public class PlaceTest {

  @Test
  public void place_instantiatesCorrectly_true() {
    Place myPlace = new Place("Canada");
    assertEquals(true, myPlace instanceof Place);
  }

  @Test
  public void place_instantiatesWithDescription_true() {
    Place myPlace = new Place("Canada");
    assertEquals("Canada", myPlace.getLocation());
  }
}
