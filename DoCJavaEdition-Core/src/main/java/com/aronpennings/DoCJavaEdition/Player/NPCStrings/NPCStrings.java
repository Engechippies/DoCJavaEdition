package com.aronpennings.DoCJavaEdition.Player.NPCStrings;

import java.util.Random;

public enum NPCStrings {
    NPC_STRINGS(
            new String[]{"Joe", "Mom", "Dad", "Dave", "Amber", "Johny", "Anthony", "Cole", "Ashley", "John Cena", "The Rock", "Sam", "Monke", "Ian", "Charlotte", "Kyle", "Mia", "Miguel", "Daniël", "Aiden", "Matt", "Olivia", "Sofia", "Samuel", "Richard", "Steve", "Alex", "Lesley", "Layla", "Hugh", "Dylan", "Dennis", "David", "Nick", "Max", "Carlos", "Oliver", "Thomas"},
            new String[]{" : im going to fuck you up like i fucked your mom", " : you will perish in front of my eyes"," : oh look at what we have here, you look easy to beat up"," : you look like an easy fight, give me everything or you will regret it", " : you look pathetic, this will be the most simple fight of my life", " : you will be another one of my many kills", " : kys, or better yet, let me kill you"},
            new String[]{" : bullshit", " : says the weakling", " : prove it in your fighting skills then", " : battle me to the death then coward"," : doubt it", " : you are going to want to take that back!", " : you made me sad, you will suffer for that", " : i will die? are you sure about that you fool?", " : i am going to crush your skull", " : i dont even have to try, so you are dead meat", " : you dare disrespect me?"},
            new String[]{" : how can this be, i have won to people more powerful than you", " : what the fuck, how??", " : but god is on my side, how could this have gone this way", " : impossible", " : you fucking bastard, you played dirty", " : you got fucking lucky bastard, rematch me and you will see", " : i will see you in hell", " : lets do a rematch pussy", " : i only went easy on you, rematch and i will give you a challenge bitch"},
            new String[]{" : that was really easy", " : that is how it is done", " : i obliterated you easily", " : pathetic", " : what a disappointment you are", ": now reality can be whatever i want", ": any last words?", " : my grandma could do better than you", " : you have wasted my time with your shitty fighting skills", " : this was supposed to be a challenge?", ": you must not have been trying, or perhaps you really are just that shit"}
    );

    private final String[] name;
    private final String[] beginSentences;
    private final String[] beginSentencesRespone;
    private final String[] endSentencesVictory;
    private final String[] endSentencesLoss;
    NPCStrings(String[] name, String[] beginSentences, String[] beginSentencesResponse, String[] endSentencesVictory, String[] endSentencesLoss) {
        this.name = name;
        this.beginSentences = beginSentences;
        this.beginSentencesRespone = beginSentencesResponse;
        this.endSentencesVictory = endSentencesVictory;
        this.endSentencesLoss = endSentencesLoss;
    }
    public String getName() {
        Random random = new Random();
        return name[random.nextInt(name.length - 1)];
    }
    public String getBeginSentences() {
        Random random = new Random();
        return beginSentences[random.nextInt(beginSentences.length - 1)];
    }
    public String getBeginSentencesRespone() {
        Random random = new Random();
        return beginSentencesRespone[random.nextInt(beginSentencesRespone.length - 1)];
    }
    public String getEndSentencesLoss() {
        Random random = new Random();
        return endSentencesLoss[random.nextInt(endSentencesLoss.length - 1)];
    }
    public String getEndSentencesVictory() {
        Random random = new Random();
        return endSentencesVictory[random.nextInt(endSentencesVictory.length - 1)];
    }
}
