package ru.vyatsu.fileconverter.core.transform;

import ru.vyatsu.fileconverter.core.model.json.*;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;
import ru.vyatsu.fileconverter.core.model.xml.Manhwa;
import ru.vyatsu.fileconverter.core.model.xml.TeamTranslation;

import java.util.*;

public class MangalibConverter {

    private MangalibConverter() {}

    public static MangalibJson mangalibToMangalibJson(MangalibXml mangalib) {
        return MangalibJson.builder()
                .mangalib(authorsToAuthorsJson(mangalib.getMangalib()))
                .build();
    }

    private static AuthorsJson authorsToAuthorsJson(List<Manhwa> manhwaList) {
        Map<String, Author> authorMap = new LinkedHashMap<>();

        for (Manhwa manhwa : manhwaList) {
            String authorName = manhwa.getAuthor();

            if (authorMap.containsKey(authorName)) {
                authorMap.get(authorName)
                        .getAuthorJson()
                        .getManhws()
                        .add(manhwaToManhwaJson(manhwa));
            } else {
                authorMap.put(authorName, manhwaToAuthor(manhwa));
            }
        }

        return AuthorsJson.builder()
                .authors(new ArrayList<>(authorMap.values()))
                .build();
    }

    private static Author manhwaToAuthor(Manhwa manhwa) {
        return Author.builder()
                .authorJson(manhwaToAuthorJson(manhwa))
                .build();
    }

    private static AuthorJson manhwaToAuthorJson(Manhwa manhwa) {
        return AuthorJson.builder()
                .name(manhwa.getAuthor())
                .manhws(new ArrayList<>(Collections.singletonList(manhwaToManhwaJson(manhwa))))
                .build();
    }

    private static ManhwaJson manhwaToManhwaJson(Manhwa manhwa) {
        return ManhwaJson.builder()
                .title(manhwa.getTitle())
                .publicationYear(manhwa.getPublicationYear())
                .chapters(manhwa.getChapters())
                .translationTeam(teamTranslationToTeamTranslationJson(manhwa.getTranslationTeam()))
                .build();
    }

    private static TeamTranslationJson teamTranslationToTeamTranslationJson(TeamTranslation teamTranslation) {
        return TeamTranslationJson.builder()
                .publicationProjects(teamTranslation.getPublicationProjects())
                .adminName(teamTranslation.getAdminName())
                .name(teamTranslation.getName())
                .build();
    }
}