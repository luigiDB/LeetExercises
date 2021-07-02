package leetfree;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if
 * IP is not a correct IP of any type.
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and
 * "192.168@1.1" are invalid IPv4 addresses.
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English
 * letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6
 * addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6
 * addresses.
 */
public class _468_ValidateIPAddress {

    @Test
    public void validIPv4() {
        assertEquals("IPv4", validIPAddress("172.16.254.1"));
    }

    @Test
    public void validIPv6() {
        assertEquals("IPv6", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
    }

    @Test
    public void invalidAddresses() {
        assertEquals("Neither", validIPAddress("256.256.256.256"));
        assertEquals("Neither", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        assertEquals("Neither", validIPAddress("1e1.4.5.6"));
        assertEquals("Neither", validIPAddress("072.16.254.1"));
    }

    public String validIPAddress(String IP) {
        int dotCounter = 0, semicolonCounter = 0;
        for (char c : IP.toCharArray()) {
            if (c == '.')
                dotCounter++;
            else if (c == ':')
                semicolonCounter++;
        }

        if (dotCounter == 3) {
            return checkIPv4(IP) ? "IPv4" : "Neither";
        } else if (semicolonCounter == 7) {
            return checkIPv6(IP) ? "IPv6" : "Neither";
        } else
            return "Neither";
    }

    private boolean checkIPv6(String IP) {
        String[] splitv6 = IP.split(":");
        for (String s : splitv6) {
            if (s.length() == 0 || s.length() > 4)
                return false;
            try {
                new BigInteger(s, 16);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIPv4(String IP) {
        String[] splitv4 = IP.split("\\.");
        if (splitv4[0].length() > 0 && splitv4[0].charAt(0) == '0')
            return false;
        for (String s : splitv4) {
            try {
                int integer = Integer.parseInt(s);
                return 0 <= integer && integer <= 255;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

}
