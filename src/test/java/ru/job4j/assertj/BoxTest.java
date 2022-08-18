package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere").isNotEmpty();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(1, 5);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty().isEqualTo("Unknown object");
    }

    @Test
    void whenNumberOfVerticesIs4() {
        Box box = new Box(4, 6);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isGreaterThan(0).isEqualTo(4).isEven();
    }

    @Test
    void whenNumberOfVerticesIsMin1() {
        Box box = new Box(-1, 5);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNegative().isOdd().isEqualTo(-1);
    }

    @Test
    void whenNumberOfVerticesIsMin1ThanFalse() {
        Box box = new Box(-1, 5);
        boolean isExistable = box.isExist();
        assertThat(isExistable).isFalse().isEqualTo(false);
    }

    @Test
    void whenNumberOfVerticesIs4ThanTrue() {
        Box box = new Box(4, 6);
        boolean isExistable = box.isExist();
        assertThat(isExistable).isTrue().isEqualTo(true);
    }

    @Test
    void whenNumberOfVerticesIs4ThanAreaIs62() {
        Box box = new Box(4, 6);
        double area = box.getArea();
        assertThat(area).isCloseTo(62.35, offset(0.01)).isNotNegative();
    }

    @Test
    void whenNumberOfVerticesIs8ThanAreaIs216() {
        Box box = new Box(8, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(216).isGreaterThan(1.0);
    }

    @Test
    void whenNumberOfVerticesIs1ThanAreaIs0() {
        Box box = new Box(1, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(0).isZero();
    }
}