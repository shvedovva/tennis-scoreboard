package com.example.model;

import jakarta.persistence.*;

import java.util.Objects;

//Таблица завершенных матчей
@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player1", nullable = false)
    private Player player1;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "player2", nullable = false)
    private Player player2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;

    public Match() {
    }

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player1=" + (player1 != null ? player1.getName() : "null") +
                ", player2=" + (player2 != null ? player2.getName() : "null") +
                ", winner=" + (winner != null ? winner.getName() : "null") +
                '}';
    }
}
