import java.util.ArrayList;
import java.util.Scanner;

class Paciente {
    private String nome;
    private String cpf;
    private int idade;
    private String telefone;
    private String pagamento;
    private String planoDeSaude;

    public Paciente(String nome, String cpf, int idade, String telefone, String pagamento, String planoDeSaude) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.pagamento = pagamento;
        this.planoDeSaude = planoDeSaude;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPagamentoPaciente() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public void setPlano(String planoDeSaude) {
        this.planoDeSaude = planoDeSaude;
    }

    public String getPlano(){
        return planoDeSaude;
    }

    public void exibirInformacoes() {
        System.out.println("Paciente: " + nome + " | CPF: " + cpf + " | Idade: " + idade +
                " | Telefone: " + telefone + " | Método de pagamento: " + pagamento + " | Plano de saúde: " + planoDeSaude + "\n");
    }
}

class Consulta {
    private Paciente paciente;
    private String data;
    private String horario;
    private String especialidade;
    private String medico;

    public Consulta(Paciente paciente, String data, String horario, String especialidade, String medico) {
        this.paciente = paciente;
        this.data = data;
        this.horario = horario;
        this.especialidade = especialidade;
        this.medico = medico;
    }

    public String getCpfPaciente() {
        return paciente.getCpf();
    }

    public void exibirDetalhes() {
        System.out.println("\nConsulta com: " + medico + " | Especialidade: " + especialidade +
                " | Data: " + data + " | Horário: " + horario + "\n");
    }
}

class SistemaHospital {
    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public void cadastrarPaciente(String nome, String cpf, int idade, String telefone, String pagamento, String planoDeSaude) {
        pacientes.add(new Paciente(nome, cpf, idade, telefone, pagamento, null));
        System.out.println("\nPaciente cadastrado com sucesso!\n");
    }

    public void agendarConsulta(String cpf, String data, String horario, String especialidade, String medico) {
        Paciente pacienteEncontrado = null;
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                pacienteEncontrado = p;
                break;
            }
        }
        if (pacienteEncontrado != null) {
            consultas.add(new Consulta(pacienteEncontrado, data, horario, especialidade, medico));
            System.out.println("\nConsulta agendada com sucesso!\n");
        } else {
            System.out.println("\nPaciente não encontrado!\n");
        }
    }

    public void alterarMeioDePagamento(Integer valorEscolha, String cpf) {
        String novoPagamento = switch (valorEscolha) {
            case 1 -> "PIX";
            case 2 -> "Crédito";
            case 3 -> "Débito";
            case 4 -> "Boleto";
            case 5 -> "Plano de Saúde";
            default -> null;
        };

        if (novoPagamento == null) {
            System.out.println("\nOpção inválida.\n");
            return;
        }

        boolean encontrou = false;
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                p.setPagamento(novoPagamento);
                System.out.println("\nMeio de pagamento alterado com sucesso!\n");
                p.exibirInformacoes();
                encontrou = true;
                break;
            }
        }

        if (!encontrou) {
            System.out.println("\nPaciente não encontrado.\n");
        }
    }

    public void buscarPlano(Integer plano, String cpf) {
        String buscaPlano = switch (plano) {
            case 1 -> "Metlife";
            case 2 -> "Unimed";
            case 3 -> "Sul America";
            case 4 -> "Amil";
            default -> null;
        };

        if (buscaPlano == null) {
            System.out.println("\nOpção inválida.\n");
            return;
        }

        boolean encontrou = false;
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                if (p.getPlano() != null && p.getPlano().equals(buscaPlano)) {
                    System.out.println("\nEste plano já está vinculado ao respectivo paciente.\n");
                    return;
                }
                p.setPlano(buscaPlano);
                System.out.println("\nPlano de saúde alterado!\n");
                p.exibirInformacoes();
                encontrou = true;
                break;
            }
        }

        if (!encontrou) {
            System.out.println("\nPaciente não encontrado.\n");
        }
    }

    public void listarConsultasPaciente(String cpf) {
        boolean encontrou = false;
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf)) {
                c.exibirDetalhes();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("\nNenhuma consulta encontrada para este CPF.\n");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaHospital sistema = new SistemaHospital();

        while (true) {
            System.out.println("\n-----------------------------------------------------");
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Agendar Consulta");
            System.out.println("3 - Listar Consultas de um Paciente");
            System.out.println("4 - Alterar Meio de Pagamento");
            System.out.println("5 - Buscar/Cadastrar Novo Plano de Saúde do Cliente");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n-----------------------------------------------------\n");
            if (opcao == 1) {
                System.out.print("\nNome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.print("Meio de pagamento: ");
                String pagamento = scanner.nextLine();
                System.out.println("-----------------------------------------------------\n");
                sistema.cadastrarPaciente(nome, cpf, idade, telefone, pagamento, null);

            } else if (opcao == 2) {
                System.out.print("\nCPF do paciente: ");
                String cpf = scanner.nextLine();
                System.out.print("Data (dd/mm/aaaa): ");
                String data = scanner.nextLine();
                System.out.print("Horário (hh:mm): ");
                String horario = scanner.nextLine();
                System.out.print("Especialidade: ");
                String especialidade = scanner.nextLine();
                System.out.print("Médico: ");
                String medico = scanner.nextLine();
                System.out.println("-----------------------------------------------------\n");
                sistema.agendarConsulta(cpf, data, horario, especialidade, medico);

            } else if (opcao == 3) {
                System.out.print("\nCPF do paciente: ");
                String cpf = scanner.nextLine();
                System.out.println("-----------------------------------------------------\n");
                sistema.listarConsultasPaciente(cpf);

            } else if (opcao == 4) {
                System.out.println("\nMeios de pagamentos disponíveis:");
                System.out.println(" 1 - PIX \n 2 - Crédito \n 3 - Débito \n 4 - Boleto \n 5 - Plano de Saúde\n");
                System.out.print("Escolha uma opção: ");
                int escolha = scanner.nextInt();
                scanner.nextLine();
                System.out.print("CPF do paciente: ");
                String cpf = scanner.nextLine();
                System.out.println("-----------------------------------------------------\n");
                sistema.alterarMeioDePagamento(escolha, cpf);

            } else if (opcao == 5){
                System.out.println("\nPlanos de Saúde Existentes:");
                System.out.println(" 1 - Metlife \n 2 - Unimed \n 3 - Sul America \n 4 - Amil\n");
                System.out.print("Escolha uma opção: ");
                int plano = scanner.nextInt();
                scanner.nextLine();
                System.out.print("CPF do paciente: ");
                String cpf = scanner.nextLine();
                System.out.println("-----------------------------------------------------\n");
                sistema.buscarPlano(plano, cpf);

            } else if (opcao == 6) {
                System.out.println("\nSaindo...\n");
                break;
            } else {
                System.out.println("\nOpção inválida! Tente novamente.\n");
            }
        }
        System.out.println("-----------------------------------------------------");
        scanner.close();
    }
}