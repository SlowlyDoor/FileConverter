package ru.vyatsu.service.converters;

import lombok.Builder;
import lombok.Data;
import ru.vyatsu.service.structure.json.*;
import ru.vyatsu.service.structure.xml.Mangalib;
import ru.vyatsu.service.structure.xml.Manhwa;
import ru.vyatsu.service.structure.xml.TeamTranslation;

import java.util.*;

@Data
@Builder
public class MangalibConverter {

    public static MangalibJson convertMangalibToMangalibJson(Mangalib mangalib) {
        return MangalibJson.builder()
                .mangalib(convertAuthorsToAuthorsJson(mangalib.getMangalib()))
                .build();
    }

    private static AuthorsJson convertAuthorsToAuthorsJson(List<Manhwa> manhwaList) {
        Map<String, Author> authorMap = new LinkedHashMap<>();

        for (Manhwa manhwa : manhwaList) {
            String authorName = manhwa.getAuthor();

            // Если автор уже существует, добавляем манхву к его списку
            if (authorMap.containsKey(authorName)) {
                authorMap.get(authorName).getAuthor().getManhws().add(convertManhwaToManhwaJson(manhwa));
            } else {
                // Создаем нового автора с манхвой и добавляем в мап
                Author author = convertManhwaToAuthor(manhwa);
                authorMap.put(authorName, author);
            }
        }

        return AuthorsJson.builder()
                .authors(new ArrayList<>(authorMap.values()))
                .build();
    }

    private static Author convertManhwaToAuthor(Manhwa manhwa) {
        return Author.builder()
                .author(convertManhwaToAuthorJson(manhwa))
                .build();
    }

    private static AuthorJson convertManhwaToAuthorJson(Manhwa manhwa) {
        return AuthorJson.builder()
                .name(manhwa.getAuthor())
                .manhws(new ArrayList<>(Collections.singletonList(convertManhwaToManhwaJson(manhwa))))
                .build();
    }

    private static ManhwaJson convertManhwaToManhwaJson(Manhwa manhwa) {
        return ManhwaJson.builder()
                .title(manhwa.getTitle())
                .publicationYear(manhwa.getPublicationYear())
                .chapters(manhwa.getChapters())
                .translationTeam(convertTeamTranslationToTeamTranslationJson(manhwa.getTranslationTeam()))
                .build();
    }

    private static TeamTranslationJson convertTeamTranslationToTeamTranslationJson(TeamTranslation teamTranslation) {
        return TeamTranslationJson.builder()
                .publicationProjects(teamTranslation.getPublicationProjects())
                .adminName(teamTranslation.getAdminName())
                .name(teamTranslation.getName())
                .build();
    }
}
