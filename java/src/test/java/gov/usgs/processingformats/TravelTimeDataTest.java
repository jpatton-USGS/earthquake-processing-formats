package gov.usgs.processingformats;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TravelTimeDataTest {

  public static String TRAVELTIMEDATA_STRING =
      "{\"LocationUseFlag\":true,"
          + "\"DistanceDerivative\":1.2,\"DepthDerivative\":3.45,"
          + "\"AssociationWeightFlag\":true,\"TeleseismicPhaseGroup\":\"P\","
          + "\"Phase\":\"Pg\",\"RayDerivative\":5.67,\"AuxiliaryPhaseGroup\":\"P\","
          + "\"Observability\":0.34,\"StatisticalSpread\":1.5,\"TravelTime\":22.456}";

  public static String PHASE = "Pg";
  public static double TRAVELTIME = 22.456;
  public static double DISTANCEDERIVATIVE = 1.2;
  public static double DEPTHDERIVATIVE = 3.45;
  public static double RAYDERIVATIVE = 5.67;
  public static double STATISTICALSPREAD = 1.5;
  public static double OBSERVABILITY = .34;
  public static String TELESEISMICPHASEGROUP = "P";
  public static String AUXILIARYPHASEGROUP = "P";
  public static boolean LOCATIONUSEFLAG = true;
  public static boolean ASSOCIATIONWEIGHTFLAG = true;

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    TravelTimeData travelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            DISTANCEDERIVATIVE,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            OBSERVABILITY,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            ASSOCIATIONWEIGHTFLAG);

    // write out to a string
    String jsonString = Utility.toJSONString(travelTimeDataObject.toJSON());

    // check the data
    try {
      checkData(new TravelTimeData(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    // build TravelTimeData object
    try {

      checkData(new TravelTimeData(Utility.fromJSONString(TRAVELTIMEDATA_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Reload function fills in members correctly */
  @Test
  public void reload() {

    // use constructor
    TravelTimeData travelTimeDataObject = new TravelTimeData();

    travelTimeDataObject.reload(
        PHASE,
        TRAVELTIME,
        DISTANCEDERIVATIVE,
        DEPTHDERIVATIVE,
        RAYDERIVATIVE,
        STATISTICALSPREAD,
        OBSERVABILITY,
        TELESEISMICPHASEGROUP,
        AUXILIARYPHASEGROUP,
        LOCATIONUSEFLAG,
        ASSOCIATIONWEIGHTFLAG);

    // check data values
    checkData(travelTimeDataObject, "Reload Function");
  }

  /** Set functions fill in members correctly */
  @Test
  public void setters() {

    // use constructor
    TravelTimeData travelTimeDataObject = new TravelTimeData();

    travelTimeDataObject.Phase = PHASE;
    travelTimeDataObject.TravelTime = TRAVELTIME;
    travelTimeDataObject.DistanceDerivative = DISTANCEDERIVATIVE;
    travelTimeDataObject.DepthDerivative = DEPTHDERIVATIVE;
    travelTimeDataObject.RayDerivative = RAYDERIVATIVE;
    travelTimeDataObject.StatisticalSpread = STATISTICALSPREAD;
    travelTimeDataObject.Observability = OBSERVABILITY;
    travelTimeDataObject.TeleseismicPhaseGroup = TELESEISMICPHASEGROUP;
    travelTimeDataObject.AuxiliaryPhaseGroup = AUXILIARYPHASEGROUP;
    travelTimeDataObject.LocationUseFlag = LOCATIONUSEFLAG;
    travelTimeDataObject.AssociationWeightFlag = ASSOCIATIONWEIGHTFLAG;

    // check data values
    checkData(travelTimeDataObject, "Set Functions");
  }

  /** Copy constructor fills in members correctly */
  @Test
  public void copyConstructor() {

    // use constructor
    TravelTimeData travelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            DISTANCEDERIVATIVE,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            OBSERVABILITY,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            ASSOCIATIONWEIGHTFLAG);

    TravelTimeData travelTimeDataObject2 = new TravelTimeData(travelTimeDataObject);

    // check data values
    checkData(travelTimeDataObject2, "Copy Constructor");
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // use constructor
    TravelTimeData travelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            DISTANCEDERIVATIVE,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            OBSERVABILITY,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            ASSOCIATIONWEIGHTFLAG);

    // Successful validation
    boolean rc = travelTimeDataObject.isValid();

    // check return code
    assertEquals("Successful Validation", true, rc);

    // use constructor
    TravelTimeData badTravelTimeDataObject =
        new TravelTimeData(
            PHASE,
            TRAVELTIME,
            null,
            DEPTHDERIVATIVE,
            RAYDERIVATIVE,
            STATISTICALSPREAD,
            null,
            TELESEISMICPHASEGROUP,
            AUXILIARYPHASEGROUP,
            LOCATIONUSEFLAG,
            null);

    rc = badTravelTimeDataObject.isValid();

    // check return code
    assertEquals("Unsuccessful Validation", false, rc);
  }

  public void checkData(TravelTimeData travelTimeDataObject, String TestName) {

    // check travelTimeDataObject.phase
    assertEquals(TestName + " Phase Equals", PHASE, travelTimeDataObject.Phase);

    // check travelTimeDataObject.travelTime
    assertEquals(TestName + " Travel Time Equals", TRAVELTIME, travelTimeDataObject.TravelTime, 0);

    // check travelTimeDataObject.distanceDerivative
    assertEquals(
        TestName + " Distance Derivative Equals",
        DISTANCEDERIVATIVE,
        travelTimeDataObject.DistanceDerivative,
        0);

    // check travelTimeDataObject.depthDerivative
    assertEquals(
        TestName + " Depth Derivative Equals",
        DEPTHDERIVATIVE,
        travelTimeDataObject.DepthDerivative,
        0);

    // check travelTimeDataObject.rayDerivative
    assertEquals(
        TestName + " Ray Derivative Equals", RAYDERIVATIVE, travelTimeDataObject.RayDerivative, 0);

    // check travelTimeDataObject.statisticalSpread
    assertEquals(
        TestName + " Statistical Spread Equals",
        STATISTICALSPREAD,
        travelTimeDataObject.StatisticalSpread,
        0);

    // check travelTimeDataObject.observability
    assertEquals(
        TestName + " Observability Equals", OBSERVABILITY, travelTimeDataObject.Observability, 0);

    // check travelTimeDataObject.teleseismicPhaseGroup
    assertEquals(
        TestName + " Teleseismic Phase Group Equals",
        TELESEISMICPHASEGROUP,
        travelTimeDataObject.TeleseismicPhaseGroup);

    // check travelTimeDataObject.auxiliaryPhaseGroup
    assertEquals(
        TestName + " Auxiliary Phase Group Equals",
        AUXILIARYPHASEGROUP,
        travelTimeDataObject.AuxiliaryPhaseGroup);

    // check OriginObject.locationUseFlag
    assertEquals(
        TestName + " Location Use Flag Equals",
        LOCATIONUSEFLAG,
        travelTimeDataObject.LocationUseFlag);

    // check travelTimeDataObject.associationWeightFlag
    assertEquals(
        TestName + " Association Use Flag Equals",
        ASSOCIATIONWEIGHTFLAG,
        travelTimeDataObject.AssociationWeightFlag);
  }
}
