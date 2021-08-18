package com.taxi;

public class Wey {

    private String from;
    private String to;

    public void in(FindTaxiRequest findTaxiRequest){

        from = findTaxiRequest.from;
        to = findTaxiRequest.to;

    }

}
