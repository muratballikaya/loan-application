package com.mb.auth.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleEnumTest {

    @Test
    void testRoleEnumValues() {
        RoleEnum[] expectedValues = {RoleEnum.ROLE_USER, RoleEnum.ROLE_ADMIN};
        assertArrayEquals(expectedValues, RoleEnum.values());
    }

    @Test
    void testRoleEnumValueOf() {
        assertEquals(RoleEnum.ROLE_USER, RoleEnum.valueOf("ROLE_USER"));
        assertEquals(RoleEnum.ROLE_ADMIN, RoleEnum.valueOf("ROLE_ADMIN"));
    }
}