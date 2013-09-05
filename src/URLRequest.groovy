
class URLRequest {
    private String url
    private URLVisitor urlVisitor

    URLRequest(String url, URLVisitor UrlVisitor) {
        this.urlVisitor = UrlVisitor
        this.url = url
    }

    String getSourceUrl() {
        return url;
    }

    void goToUrl() {
        urlVisitor.prepareForVisit()
        if (isUrlValid()) {

        }
    }

    boolean isUrlValid() {
        return url != null
    }
}
