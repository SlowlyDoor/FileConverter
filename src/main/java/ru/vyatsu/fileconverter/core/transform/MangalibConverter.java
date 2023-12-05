package ru.vyatsu.fileconverter.core.transform;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.core.model.json.*;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;
import ru.vyatsu.fileconverter.core.model.xml.Manhwa;
import ru.vyatsu.fileconverter.core.model.xml.TeamTranslation;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class MangalibConverter {

    public MangalibJson mangalibToMangalibJson(MangalibXml mangalib) {
        return MangalibJson.builder()
                .mangalib(authorsToAuthorsJson(mangalib.getMangalib()))
                .build();
    }

    private AuthorsJson authorsToAuthorsJson(List<Manhwa> manhwaList) {
        List<Map<String, AuthorJson>> authorsList = new ArrayList<>();
        Set<String> addedAuthors = new LinkedHashSet<>();

        for (Manhwa manhwa : manhwaList) {
            String authorName = manhwa.getAuthor();
            if (!addedAuthors.contains(authorName)) {
                Map<String, AuthorJson> authorMap = new HashMap<>();
                authorMap.put("author", authorToAuthorJson(authorName, manhwaList));
                authorsList.add(authorMap);
                addedAuthors.add(authorName);
            }
        }

        return AuthorsJson.builder()
                .authors(authorsList)
                .build();
    }

    private AuthorJson authorToAuthorJson(String authorName, List<Manhwa> manhwaList) {
        return AuthorJson.builder()
                .name(authorName)
                .manhws(manhwaList.stream()
                        .filter(m -> authorName.equals(m.getAuthor()))
                        .map(MangalibConverter::manhwaToManhwaJson)
                        .collect(Collectors.toList()))
                .build();
    }

    private ManhwaJson manhwaToManhwaJson(Manhwa manhwa) {
        return ManhwaJson.builder()
                .title(manhwa.getTitle())
                .publicationYear(manhwa.getPublicationYear())
                .chapters(manhwa.getChapters())
                .translationTeam(teamTranslationToTeamTranslationJson(manhwa.getTranslationTeam()))
                .build();
    }

    private TeamTranslationJson teamTranslationToTeamTranslationJson(TeamTranslation teamTranslation) {
        return TeamTranslationJson.builder()
                .publicationProjects(teamTranslation.getPublicationProjects())
                .adminName(teamTranslation.getAdminName())
                .name(teamTranslation.getName())
                .build();
    }
}
