package ru.vyatsu.service.converters;

import ru.vyatsu.service.structure.json.*;
import ru.vyatsu.service.structure.xml.Mangalib;
import ru.vyatsu.service.structure.xml.Manhwa;
import ru.vyatsu.service.structure.xml.TeamTranslation;

import java.util.ArrayList;
import java.util.List;

public class MangalibJsonConverter {

    private static int currentId = 0;

    public static Mangalib convertMangalibJsonToMangalib(MangalibJson mangalibJson) {
        List<Manhwa> manhwaList = new ArrayList<>();

        if (mangalibJson != null && mangalibJson.getMangalib() != null && mangalibJson.getMangalib().getAuthors() != null) {
            for (Author authorJson : mangalibJson.getMangalib().getAuthors()) {
                if (authorJson.getAuthor() != null) {
                    manhwaList.addAll(convertAuthorJsonToManhwaList(authorJson.getAuthor()));
                }
            }
        }

        return Mangalib.builder().mangalib(manhwaList).build();
    }

    private static List<Manhwa> convertAuthorJsonToManhwaList(AuthorJson authorJson) {
        List<Manhwa> manhwaList = new ArrayList<>();
        if (authorJson.getManhws() != null) {
            for (ru.vyatsu.service.structure.json.ManhwaJson manhwaJson : authorJson.getManhws()) {
                manhwaList.add(convertManhwaJsonToManhwa(manhwaJson, authorJson.getName()));
            }
        }
        return manhwaList;
    }

    private static Manhwa convertManhwaJsonToManhwa(ManhwaJson manhwaJson, String authorName) {
        return Manhwa.builder()
                .id(generateId())
                .title(manhwaJson.getTitle())
                .author(authorName)
                .publicationYear(manhwaJson.getPublicationYear())
                .chapters(manhwaJson.getChapters())
                .translationTeam(convertTeamTranslationJsonToTeamTranslation(manhwaJson.getTranslationTeam()))
                .build();
    }

    private static TeamTranslation convertTeamTranslationJsonToTeamTranslation(TeamTranslationJson teamTranslationJson) {
        return TeamTranslation.builder()
                .name(teamTranslationJson.getName())
                .publicationProjects(teamTranslationJson.getPublicationProjects())
                .adminName(teamTranslationJson.getAdminName())
                .build();
    }

    private static String generateId() {
        return String.valueOf(currentId++);
    }
}
