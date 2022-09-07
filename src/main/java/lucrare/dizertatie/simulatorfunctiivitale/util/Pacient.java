package lucrare.dizertatie.simulatorfunctiivitale.util;

public class Pacient {

    private Integer id;

    private static Pacient pacientInstance = null;

    private Pacient(Integer id) {
        this.id = id;
    }

    public static Pacient getInstance(Integer id) {
        if (pacientInstance == null)
            pacientInstance = new Pacient(id);
        return pacientInstance;
    }

    public Integer getId() {
        return id;
    }

    public static String addPacientToNewsReport(String newsReport, Integer pacientId) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n")
                .append("pacientId : ")
                .append(pacientId)
                .append("\n")
                .append(newsReport);
        return stringBuilder.toString();
    }

}
