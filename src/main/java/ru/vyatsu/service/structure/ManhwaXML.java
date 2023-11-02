package ru.vyatsu.service.structure;

import lombok.*;

@Getter
@Setter
public class ManhwaXML
{
    private int id; // Идентификатор
    private String manhwa; // Название манхвы
    private String author; // Имя автора
    private int publicationYear; // Год публикации манхвы
    private int chapters; // Количество глав
    private TeamTranslation translationTeam; // Группа переводчиков


}
