package ru.vyatsu.fileconverter.core.transform;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.core.model.json.*;
import ru.vyatsu.fileconverter.core.model.xml.MangalibXml;
import ru.vyatsu.fileconverter.core.model.xml.Manhwa;
import ru.vyatsu.fileconverter.core.model.xml.TeamTranslation;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MangalibJsonConverter {

    private int currentId = 0;

    public MangalibXml mangalibJsonToMangalib(MangalibJson mangalibJson) {
        List<Manhwa> manhwaList = new ArrayList<>();

        if (mangalibJson != null && mangalibJson.getMangalib() != null && mangalibJson.getMangalib().getAuthors() != null) {
            for (Author authorJson : mangalibJson.getMangalib().getAuthors()) {
                if (authorJson.getAuthorJson() != null) {
                    manhwaList.addAll(authorJsonToManhwaList(authorJson.getAuthorJson()));
                }
            }
        }

        return MangalibXml.builder().mangalib(manhwaList).build();
    }

    private List<Manhwa> authorJsonToManhwaList(AuthorJson authorJson) {
        List<Manhwa> manhwaList = new ArrayList<>();

        if (authorJson.getManhws() != null) {
            for (ManhwaJson manhwaJson : authorJson.getManhws()) {
                manhwaList.add(manhwaJsonToManhwa(manhwaJson, authorJson.getName()));
            }
        }
        return manhwaList;
    }

    private Manhwa manhwaJsonToManhwa(ManhwaJson manhwaJson, String authorName) {
        return Manhwa.builder()
                .id(generateId())
                .title(manhwaJson.getTitle())
                .author(authorName)
                .publicationYear(manhwaJson.getPublicationYear())
                .chapters(manhwaJson.getChapters())
                .translationTeam(teamTranslationJsonToTeamTranslation(manhwaJson.getTranslationTeam()))
                .build();
    }

    private TeamTranslation teamTranslationJsonToTeamTranslation(TeamTranslationJson teamTranslationJson) {
        return TeamTranslation.builder()
                .name(teamTranslationJson.getName())
                .publicationProjects(teamTranslationJson.getPublicationProjects())
                .adminName(teamTranslationJson.getAdminName())
                .build();
    }

    private String generateId() {
        return String.valueOf(currentId++);
    }
}
