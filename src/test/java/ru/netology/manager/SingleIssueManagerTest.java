package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SingleIssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);


    Issue first = new Issue(1, "name1", "author1", Set.of("label1"), "assignee2", true, 1);
    Issue second = new Issue(2, "name2", "author1", Set.of("label2"), "assignee2", false, 2);
    Issue third = new Issue(3, "name3", "author2", Set.of("label3"), "assignee1", false, 3);
    Issue fourth = new Issue(4, "name4", "author1", Set.of("label4"), "assignee2", true, 1);
    Issue fifth = new Issue(5, "name5", "author2", Set.of("label1"), "assignee2", false, 2);
    Issue sixth = new Issue(6, "name6", "author2", Set.of("label6"), "assignee1", true, 4);


    @Test
    public void shouldFindOneIssueIfOpened() {

        manager.add(fourth);

        Collection<Issue> actual = manager.findIfOpened();
        Collection<Issue> expected = List.of(fourth);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldOpenByIdOneIssue() {

        manager.add(third);
        manager.openById(3);

        assertTrue(third.getIsOpened());
    }

    @Test
    public void shouldCloseByIdOneIssue() {

        manager.add(sixth);
        manager.closeById(6);

        assertFalse(sixth.getIsOpened());
    }

    @Test
    public void shouldFilterByAuthorOneIssue() {

        manager.add(second);

        Collection<Issue> actual = manager.filterByAuthor("author1");
        Collection<Issue> expected = List.of(second);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByLabelOneIssue() {

        manager.add(first);

        Set<Issue> actual = manager.filterByLabel(Set.of("label1"));
        Set<Issue> expected = Set.of(first);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssigneeOneIssue() {

        manager.add(fifth);

        Collection<Issue> actual = manager.filterByAssignee("assignee2");
        Collection<Issue> expected = List.of(fifth);

        assertEquals(expected, actual);
    }

}