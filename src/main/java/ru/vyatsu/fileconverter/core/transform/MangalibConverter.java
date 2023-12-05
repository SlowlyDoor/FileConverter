package ru.vyatsu.fileconverter.core.transform;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.core.model.json.*;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;
import ru.vyatsu.fileconverter.core.model.xml.Manhwa;
import ru.vyatsu.fileconverter.core.model.xml.TeamTranslation;

import java.util.*;

@UtilityClass
public class MangalibConverter {

    public MangalibJson mangalibToMangalibJson(MangalibXml mangalib) {
        return MangalibJson.builder()
                .mangalib(authorsToAuthorsJson(mangalib.getMangalib()))
                .build();
    }

    private AuthorsJson authorsToAuthorsJson(List<Manhwa> manhwaList) {
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

    private Author manhwaToAuthor(Manhwa manhwa) {
        return Author.builder()
                .authorJson(manhwaToAuthorJson(manhwa))
                .build();
    }

    private AuthorJson manhwaToAuthorJson(Manhwa manhwa) {
        return AuthorJson.builder()
                .name(manhwa.getAuthor())
                .manhws(new ArrayList<>(Collections.singletonList(manhwaToManhwaJson(manhwa))))
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