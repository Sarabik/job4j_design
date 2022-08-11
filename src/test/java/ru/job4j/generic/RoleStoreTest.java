package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsDuke() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Duke");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        Role result = store.findById("11");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsDuke() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        store.add(new Role("3", "Lady"));
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Duke");
    }

    @Test
    void whenReplaceThenRoleNameIsLady() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        store.replace("3", new Role("3", "Lady"));
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Lady");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        store.replace("30", new Role("30", "Lady"));
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Duke");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        store.delete("3");
        Role result = store.findById("3");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsDuke() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        store.delete("30");
        Role result = store.findById("3");
        assertThat(result.getRoleName()).isEqualTo("Duke");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        boolean rsl = store.replace("3", new Role("3", "Lady"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        boolean rsl = store.replace("30", new Role("30", "Lady"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        boolean rsl = store.delete("3");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Duke"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}