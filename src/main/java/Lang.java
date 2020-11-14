class Lang {
    private Long id;
    private String welcomeMessage;
    private String codeLanguage;

    public Lang(Long id, String welcomeMessage, String codeLanguage) {
        this.id = id;
        this.welcomeMessage = welcomeMessage;
        this.codeLanguage = codeLanguage;
    }

    public Long getId() {
        return id;
    }


    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }
}
