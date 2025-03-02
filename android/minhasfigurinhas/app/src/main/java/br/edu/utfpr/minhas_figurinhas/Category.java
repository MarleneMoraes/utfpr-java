package br.edu.utfpr.minhas_figurinhas;

public enum Category {
    Sports(R.string.sports),
    Movies(R.string.movies),
    TV_Shows(R.string.tv_shows),
    Celebrities(R.string.celebrities),
    Cartoons(R.string.cartoons),
    Anime_and_Manga(R.string.anime_manga),
    History_and_Culture(R.string.history_culture),
    Nature_and_Science(R.string.nature_science),
    Space_and_Astronomy(R.string.space_astronomy),
    Themed(R.string.themed),
    Commemorative_Editions(R.string.commemorative_editions),
    Games(R.string.games),
    None(R.string.none);

    private final int resourceId;

    Category(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }
}
