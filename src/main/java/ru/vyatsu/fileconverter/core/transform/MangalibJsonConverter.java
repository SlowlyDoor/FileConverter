package ru.vyatsu.fileconverter.core.transform;


import ru.vyatsu.fileconverter.core.model.json.*;
import ru.vyatsu.fileconverter.core.model.xml.Mangalib;
import ru.vyatsu.fileconverter.core.model.xml.Manhwa;
import ru.vyatsu.fileconverter.core.model.xml.TeamTranslation;

import java.util.ArrayList;
import java.util.List;

public class MangalibJsonConverter {

    private static int currentId = 0;

    public static Mangalib mangalibJsonToMangalib(MangalibJson mangalibJson) {
        List<Manhwa> manhwaList = new ArrayList<>();

        if (mangalibJson != null && mangalibJson.getMangalib() != null && mangalibJson.getMangalib().getAuthors() != null) {
            for (Author authorJson : mangalibJson.getMangalib().getAuthors()) {
                if (authorJson.getAuthor() != null) {
                    manhwaList.addAll(authorJsonToManhwaList(authorJson.getAuthor()));
                }
            }
        }

        return Mangalib.builder().mangalib(manhwaList).build();
    }

    private static List<Manhwa> authorJsonToManhwaList(AuthorJson authorJson) {
        List<Manhwa> manhwaList = new ArrayList<>();
        if (authorJson.getManhws() != null) {
            for (ManhwaJson manhwaJson : authorJson.getManhws()) {
                manhwaList.add(manhwaJsonToManhwa(manhwaJson, authorJson.getName()));
            }
        }
        return manhwaList;
    }

    private static Manhwa manhwaJsonToManhwa(ManhwaJson manhwaJson, String authorName) {
        return Manhwa.builder()
                .id(generateId())
                .title(manhwaJson.getTitle())
                .author(authorName)
                .publicationYear(manhwaJson.getPublicationYear())
                .chapters(manhwaJson.getChapters())
                .translationTeam(teamTranslationJsonToTeamTranslation(manhwaJson.getTranslationTeam()))
                .build();
    }

    private static TeamTranslation teamTranslationJsonToTeamTranslation(TeamTranslationJson teamTranslationJson) {
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
