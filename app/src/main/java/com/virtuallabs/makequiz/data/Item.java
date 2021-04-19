package com.virtuallabs.makequiz.data;

public class Item {
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    String pregunta;
    String respuesta;

    @Override
    public String toString() {
        return "\n"+pregunta+": "+respuesta;
    }

    static class FirebaseConnect {
    }
}
