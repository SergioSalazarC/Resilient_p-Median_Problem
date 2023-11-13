# Resilient_p-Median_Problem

This projects consist in the develop of 3 algorithms to solve the Resilient p-median problem, that could be formulated as:

Consider a set $L$ of m facilities, a set $U$ of $n$ users and a $n\times m$ matrix $D$ with the distances for satisfying the demand of the user located at $i$ from facility located at $j$, for all $j \in L$ and $i \in U$. The objective is to minimize the value $F(S)$:
$$(min) \; F(S) = \sum\limits_{i\in U} max(\min\limits_{j\in S} d_{ij}, \min\limits_{j' \in S \; j'\neq j}d_{ij'})$$
where $S$ (a given solution) is a multiset (allows copies) of elements of $L$ ($S \subset L$) and $|J| = p$.

The algorithms are based in VND metaheuristic and provides good solution in small execution times.

This project consist in the principal work for the subject: Resolución de problemas con metaheurísticos, for the postgrade: Master en Investigacion en Inteligencia Artificial.
