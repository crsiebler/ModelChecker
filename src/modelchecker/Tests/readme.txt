1) Input file for 100pt: exmp01_pt100.txt

You can see the model in the figure 
exmp01_pt100_sys.gif
and the specification in the figure 
exmp01_pt100_spec.gif

The negation of the specification in DFA is shown in
exmp01_pt100_spec_neg_dfa.gif
and in a minimized automaton in 
exmp01_pt100_spec_neg_dfa_min.gif

The product of the negation of the spec and the model is shown in figures
exmp01_pt100_prod.gif
exmp01_pt100_prod_min.gif

Running exmp01_pt100.txt the software should return the string "bab"

If not, take a look at the product automaton to see if the string that your software returns is part of the language.
Your string might be different because you used DFS or because multiple final states are reachable at the same path length.

2) Input file for 80pt: exmp01_pt80.txt

You can see the specification in the figure 
exmp01_pt80_spec.gif

The result should be the same as above, i.e., "bab".
If not take a look at the product automaton in 
exmp01_pt100_prod_min.gif

3) Input file for 60pt: exmp01_pt60.txt

You can see the specification in the figure 
exmp01_pt60_spec.gif

In this case, the result should be "ab".
If not look at the figure 
exmp01_pt60_spec_neg.gif
to make sure that the returned string is accepted by the automaton.

4) Input file for 40pt: exmp01_pt40.txt

You can see the specification in the figure 
exmp01_pt60_spec_neg.gif

In this case, the result should be "ab".
If not look at the figure 
exmp01_pt60_spec_neg.gif
to make sure that the returned string is accepted by the automaton.


