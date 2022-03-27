package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;


    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public Collection<Issue> findIfOpened() {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getIsOpened()) {
                result.add(issue);
            }
        }
        Collections.sort(result);
        return result;
    }

    public boolean openById(int id) {
        for (Issue issue : repository.getAll()) {
            if (issue.getId() == id) {
                return issue.setIsOpened(true);
            }
        }
        return false;
    }

    public boolean closeById(int id) {
        for (Issue issue : repository.getAll()) {
            if (issue.getId() == id) {
                return issue.setIsOpened(false);
            }
        }
        return true;
    }

    public Set<Issue> filterByLabel(Set<String> label) {
        return filterByForSet(e -> e.getLabel().equals(label));
    }

    public Collection<Issue> filterByAuthor(String text) {
        return filterByForList(e -> e.getAuthor().equals(text));
    }

    public Collection<Issue> filterByAssignee(String text) {
        return filterByForList(e -> e.getAssignee().equals(text));
    }

    private Set<Issue> filterByForSet(Predicate<Issue> filter) {
        Set<Issue> result = new HashSet<>();
        for (Issue issue : repository.getAll()) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    private Collection<Issue> filterByForList(Predicate<Issue> filter) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        Collections.sort(result);
        return result;
    }
}