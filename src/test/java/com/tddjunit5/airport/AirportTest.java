package com.tddjunit5.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @DisplayName("Given there is an business flight")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
        }

        @Test
        public void testBusinessFlightUsualPassenger() {
            Passenger mike = new Passenger("Mike", false);

            assertFalse(businessFlight.addPassenger(mike));
            assertEquals(0, businessFlight.getPassengerList().size());
            assertFalse(businessFlight.removePassenger(mike));
            assertEquals(0, businessFlight.getPassengerList().size());
        }

        @Test
        public void testBusinessFlightVipPassenger() {
            Passenger john = new Passenger("John", true);

            assertTrue(businessFlight.addPassenger(john));
            assertEquals(1, businessFlight.getPassengerList().size());
            assertFalse(businessFlight.removePassenger(john));
            assertEquals(1, businessFlight.getPassengerList().size());
        }
    }

    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }

        @DisplayName("When we have a usual passenger")
        @Nested
        class UsualPassenger {

            @DisplayName("Then you can add and remove him from an economy flight")
            @Test
            public void testEconomyFlightUsualPassenger() {
                assertAll("Verify all conditions for a usual passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengerList().size()),
                        () -> assertEquals("Mike", economyFlight.getPassengerList().get(0).getName()),
                        () -> assertTrue(economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengerList().size())
                );
            }

        }

        @DisplayName("When we have a VIP passenger")
        @Nested
        class VipPassenger {

            @DisplayName("Then you can add him but cannot remove him from an economy flight")
            @Test
            public void testEconomyFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(john)),
                        () -> assertEquals(1, economyFlight.getPassengerList().size()),
                        () -> assertEquals("John", economyFlight.getPassengerList().get(0).getName()),
                        () -> assertFalse(economyFlight.removePassenger(john)),
                        () -> assertEquals(1, economyFlight.getPassengerList().size())
                );
            }
        }
    }

    @DisplayName("Given there is a premium flight")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }
    }

}
