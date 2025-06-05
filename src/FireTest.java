import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FireTest {
    @Test
    public void testTimeToBurnExample() {
        char[][] forest = {
                { 't', '.', '.', 't', 't', 't', 't', '.', 't' },
                { '.', '.', 't', 't', '.', '.', '.', '.', 't' },
                { '.', '.', 't', 't', 't', 't', 't', 't', 't' },
                { 't', 't', 't', 't', '.', '.', '.', '.', '.' }
        };

        int matchR = 2;
        int matchC = 6;

        int expected = 8;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    @Test
    public void testNeighborTrees_bottomRightCornerBlocked() {
        char[][] forest = {
                { 't', '.', '.', 't', 't', 't', 't', '.', 't' },
                { '.', '.', 't', 't', '.', '.', '.', '.', 't' },
                { '.', '.', 't', 't', 't', 't', 't', 't', '.' },
                { 't', 't', 't', 't', '.', '.', '.', '.', 't' }
        };

        int[] location = { 3, 8, 0 };
        List<int[]> result = Fire.neighborTrees(forest, location);

        assertEquals(0, result.size());

    }

    @Test
    public void testNeighborTrees_bottomRightCornerUpOnly() {
        char[][] forest = {
                { 't', '.', '.', 't', 't', 't', 't', '.', 't' },
                { '.', '.', 't', 't', '.', '.', '.', '.', 't' },
                { '.', '.', 't', 't', 't', 't', 't', 't', 't' },
                { 't', 't', 't', 't', '.', '.', '.', '.', 't' }
        };

        int[] location = { 3, 8, 2 };
        List<int[]> result = Fire.neighborTrees(forest, location);

        assertEquals(1, result.size());
        assertEquals(2, result.get(0)[0]);
        assertEquals(8, result.get(0)[1]);
        assertEquals(3, result.get(0)[2]);
    }

    @Test
    public void testNeighborTrees_middleAllPossible() {
        char[][] forest = {
                { 't', '.', '.', 't', 't', 't', 't', '.', 't' },
                { '.', '.', 't', 't', 't', '.', '.', '.', 't' },
                { '.', '.', 't', 't', 't', 't', 't', 't', 't' },
                { 't', 't', 't', 't', 't', '.', '.', '.', 't' }
        };

        int[] location = { 2, 4, 4 };
        List<int[]> result = Fire.neighborTrees(forest, location);

        assertEquals(4, result.size());
        assertEquals(1, result.get(0)[0]);
        assertEquals(4, result.get(0)[1]);
        assertEquals(5, result.get(0)[2]);
        assertEquals(3, result.get(1)[0]);
        assertEquals(4, result.get(1)[1]);
        assertEquals(5, result.get(1)[2]);
        assertEquals(2, result.get(2)[0]);
        assertEquals(3, result.get(2)[1]);
        assertEquals(5, result.get(2)[2]);
        assertEquals(2, result.get(3)[0]);
        assertEquals(5, result.get(3)[1]);
        assertEquals(5, result.get(3)[2]);
    }

    @Test
    public void testNeighborTrees_middleNonePossible() {
        char[][] forest = {
                { 't', '.', '.', 't', 't', 't', 't', '.', 't' },
                { '.', '.', 't', '.', '.', '.', '.', '.', 't' },
                { '.', '.', 't', '.', 't', '.', 't', 't', 't' },
                { 't', 't', 't', 't', '.', '.', '.', '.', 't' }
        };

        int[] location = { 2, 4, 4 };
        List<int[]> result = Fire.neighborTrees(forest, location);

        assertEquals(0, result.size());
    }
}
