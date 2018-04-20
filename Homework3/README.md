#Assignment 3

I used Stanford NLP lemmatization library from below URL for lemmatizing the Cranfield collection::<br/>
	http://nlp.stanford.edu/software/corenlp.shtml#Download

Commands to run the program on the terminal (at the current directory)::

1.	source /usr/local/corenlp350/classpath.sh
2.	javac *.java
3.  java Homework3
4.	Output: (Answers are mentioned in SEPARATE_README.txt file)

Adding annotator tokenize
TokenizerAnnotator: No tokenizer type provided. Defaulting to PTBTokenizer.
Adding annotator ssplit
edu.stanford.nlp.pipeline.AnnotatorImplementations:
Adding annotator pos
Reading POS tagger model from edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger ... done [0.8 sec].
Adding annotator lemma
....Cranfield/.DS_Store
==========================================================================================================
Query: what similarity laws must be obeyed when constructing aeroelastic models of heated high speed aircraft

Vector Representation of Query with Raw Count  - 
[aeroelastic:1, aircraft:1, construct:1, heated:1, high:1, law:1, model:1, must:1, obey:1, similarity:1, speed:1, ]

Vector Representation of Query by Weight Schema W1 - 
[high:0.20172402601334635, law:0.3454351217699856, obey:0.5841340850404995, similarity:0.3454351217699856, aircraft:0.29963224559014, heated:0.4404229892838603, model:0.20172402601334635, must:0.3454351217699856, construct:0.4209284338656756, aeroelastic:0.4629612348432662, speed:0.16684341539219427, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.07588626172692015 : 573 :  :  viscous hypersonic similitude . 
2 : 0.06098643355360719 : 51 :  :  theory of aircraft structural models subjected to aerodynamic heating and external loads . 
3 : 0.05917882242857677 : 486 :  :  similarity laws for aerothermoelastic testing . 
4 : 0.05793312838078131 : 329 :  :  various aerodynamic characteristics in hypersonic rarefied gas flow . 
5 : 0.05353786842557894 : 13 :  :  similarity laws for stressing heated wings . 


Vector Representation of Query by Weight Schema W2 - 
[high:0.4970785020361038, law:0.5662386223139857, obey:0.6811110956124282, similarity:0.5662386223139857, aircraft:0.5441962573247486, heated:0.6119509753345465, model:0.4970785020361038, must:0.5662386223139857, construct:0.6025693351042847, aeroelastic:0.6227973735582475, speed:0.4802924131595016, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.056827390536725156 : 573 :  :  viscous hypersonic similitude . 
2 : 0.05311400284531517 : 486 :  :  similarity laws for aerothermoelastic testing . 
3 : 0.05297080289481881 : 51 :  :  theory of aircraft structural models subjected to aerodynamic heating and external loads . 
4 : 0.05109920827789528 : 329 :  :  various aerodynamic characteristics in hypersonic rarefied gas flow . 
5 : 0.04083170570903694 : 14 :  :  piston theory - a new aerodynamic tool for the aeroelastician . 
==========================================================================================================
==========================================================================================================
Query: what are the structural and aeroelastic problems associated with flight of high speed aircraft

Vector Representation of Query with Raw Count  - 
[aeroelastic:1, aircraft:1, associate:1, flight:1, high:1, problem:1, speed:1, structural:1, ]

Vector Representation of Query by Weight Schema W1 - 
[flight:0.248579354304765, high:0.20172402601334635, problem:0.1437110957566392, structural:0.37709270670419626, aircraft:0.29963224559014, aeroelastic:0.4629612348432662, associate:0.3105545111488335, speed:0.16684341539219427, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.11074479990444104 : 12 :  :  some structural and aerelastic considerations of high speed flight . 
2 : 0.07401221148177058 : 14 :  :  piston theory - a new aerodynamic tool for the aeroelastician . 
3 : 0.06772757206685119 : 51 :  :  theory of aircraft structural models subjected to aerodynamic heating and external loads . 
4 : 0.06549503335765423 : 781 :  :  use of subsonic kernel function in an influence-coefficient method of aeroelastic analysis and some comparisons with experiment . 
5 : 0.06425730716607493 : 746 :  :  aeroelastic problems in connection with high speed flight . 


Vector Representation of Query by Weight Schema W2 - 
[flight:0.5228605251330595, high:0.49970224533437696, problem:0.47102931271782467, structural:0.5863783422364303, aircraft:0.5480934534686608, aeroelastic:0.6288189241949569, associate:0.5534917910978533, speed:0.48246247838002865, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.08362143321668489 : 12 :  :  some structural and aerelastic considerations of high speed flight . 
2 : 0.06272516237531411 : 172 :  :  some aerodynamic considerations of nozzle afterbody combination . 
3 : 0.05843974386278755 : 14 :  :  piston theory - a new aerodynamic tool for the aeroelastician . 
4 : 0.05645504316148179 : 51 :  :  theory of aircraft structural models subjected to aerodynamic heating and external loads . 
5 : 0.05569943916548481 : 746 :  :  aeroelastic problems in connection with high speed flight . 
==========================================================================================================
==========================================================================================================
Query: what problems of heat conduction in composite slabs have been solved so far

Vector Representation of Query with Raw Count  - 
[composite:1, conduction:1, far:1, heat:1, problem:1, slab:1, so:1, solve:1, ]

Vector Representation of Query by Weight Schema W1 - 
[heat:0.16684341539219427, problem:0.1437110957566392, composite:0.5228299055717928, far:0.36856744140554065, conduction:0.36856744140554065, solve:0.2937068818903161, so:0.273579573891666, slab:0.47739792654102786, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.11392951177468064 : 485 :  :  linear heat flow in a composite slab . 
2 : 0.10776518038989538 : 5 :  :  one-dimensional transient heat conduction into a double-layer slab subjected to a linear heat input for a small time internal . 
3 : 0.10287287134289234 : 144 :  :  heat flow in composite slabs . 
4 : 0.09812185076846383 : 399 :  :  conduction of heat in composite slabs . 
5 : 0.091161862375825 : 90 :  :  periodic temperature distributions in a two-layer composite slab . 


Vector Representation of Query by Weight Schema W2 - 
[heat:0.48246247838002865, problem:0.47102931271782467, composite:0.65840905787801, far:0.5821647237144056, conduction:0.5821647237144056, solve:0.5451648381868293, so:0.5352169016932893, slab:0.635954269477882, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.06287607392042753 : 485 :  :  linear heat flow in a composite slab . 
2 : 0.062225483541545196 : 5 :  :  one-dimensional transient heat conduction into a double-layer slab subjected to a linear heat input for a small time internal . 
3 : 0.05991119414203442 : 399 :  :  conduction of heat in composite slabs . 
4 : 0.05971788114470243 : 144 :  :  heat flow in composite slabs . 
5 : 0.05475791752574485 : 579 :  :  further developments of new methods in heat flow analysis . 
==========================================================================================================
==========================================================================================================
Query: can a criterion be developed to show empirically the validity of flow solutions for chemically reacting gas mixtures based on the simplifying assumption of instantaneous local chemical equilibrium

Vector Representation of Query with Raw Count  - 
[assumption:1, base:1, can:1, chemical:1, chemically:1, criterion:1, develop:1, empirically:1, equilibrium:1, flow:1, gas:1, instantaneous:1, local:1, mixture:1, react:1, show:1, simplify:1, solution:1, to:1, validity:1, ]

Vector Representation of Query by Weight Schema W1 - 
[chemically:0.5354108567977349, criterion:0.38746792274757613, show:0.11388834885591022, instantaneous:0.5354108567977349, react:0.5650854040412232, develop:0.1857438967342298, local:0.25759944461254947, can:0.1437110957566392, solution:0.11388834885591022, chemical:0.37709270670419626, mixture:0.4404229892838603, gas:0.22777669771182044, assumption:0.25759944461254947, equilibrium:0.2937068818903161, simplify:0.32504302271399976, to:0.0, validity:0.41729066964830525, empirically:0.6791219525543741, flow:0.0, base:0.1857438967342298, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.07278418182916005 : 166 :  :  flow of chemically reacting gas mixtures . 
2 : 0.07145053311236314 : 1061 :  :  turbulent mixing of a rocket exhaust jet with a supersonic stream including chemical reactions . 
3 : 0.06713325376388093 : 488 :  :  a reaction-rate parameter for gasdynamics of a chemically reacting gas mixture . 
4 : 0.04917926843438996 : 1189 :  :  nonequilibrium flow past a wedge . 
5 : 0.043455669248947046 : 185 :  :  some possibilities of using gas mixtures other than in aerodynamic research . 


Vector Representation of Query by Weight Schema W2 - 
[chemically:0.6388099140852211, criterion:0.5728227587605377, show:0.4507977499153743, instantaneous:0.6388099140852211, react:0.6520456861801684, develop:0.4828475617514659, local:0.5148973735875575, can:0.46409962367218327, solution:0.4507977499153743, chemical:0.568195089335315, mixture:0.5964423673832381, gas:0.5015954998307486, assumption:0.5148973735875575, equilibrium:0.5310024149490898, simplify:0.5449793095205298, to:0.4, validity:0.5861246325173467, empirically:0.7029095377574044, flow:0.4, base:0.4828475617514659, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.04129524037221222 : 1061 :  :  turbulent mixing of a rocket exhaust jet with a supersonic stream including chemical reactions . 
2 : 0.041165875082152616 : 166 :  :  flow of chemically reacting gas mixtures . 
3 : 0.03539621320455626 : 1255 :  :  the flow about a charged body moving in the lower atmosphere . 
4 : 0.030765238074262832 : 575 :  :  atomic recombination in a hypersonic wind tunnel nozzle . 
5 : 0.030503225140316784 : 1189 :  :  nonequilibrium flow past a wedge . 
==========================================================================================================
==========================================================================================================
Query: what chemical kinetic system is applicable to hypersonic aerodynamic problems

Vector Representation of Query with Raw Count  - 
[aerodynamic:1, applicable:1, chemical:1, hypersonic:1, kinetic:1, problem:1, system:1, to:1, ]

Vector Representation of Query by Weight Schema W1 - 
[problem:0.1437110957566392, system:0.2874221915132784, chemical:0.37709270670419626, hypersonic:0.2155666436349588, applicable:0.3052371588258767, to:0.0, aerodynamic:0.2155666436349588, kinetic:0.4343232510389948, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.08526069705173638 : 103 :  :  theory of mixing and chemical reaction in the opposed jet diffusion flame . 
2 : 0.07941209006919613 : 625 :  :  viscous and inviscid nonequilibrium gas flows . 
3 : 0.07704429463016899 : 943 :  :  compressible free shear layer with finite initial thickness . 
4 : 0.07384248325271298 : 1032 :  :  on the conservativeness of various distributed force systems . 
5 : 0.07187878844257438 : 1296 :  :  non-equilibrium expansions of air with coupled chemical reactions . 


Vector Representation of Query by Weight Schema W2 - 
[problem:0.47102931271782467, system:0.5420586254356493, chemical:0.5863783422364303, hypersonic:0.506543969076737, applicable:0.5508636858775179, to:0.4, aerodynamic:0.506543969076737, kinetic:0.614664580046846, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.05277573893182577 : 625 :  :  viscous and inviscid nonequilibrium gas flows . 
2 : 0.04290686523396662 : 401 :  :  inviscid hypersonic airflows with coupled non-equilibrium processes . 
3 : 0.04178961261979255 : 1147 :  :  heat transfer to bodies traveling at high speed in the upper atmosphere . 
4 : 0.04166270674187467 : 163 :  :  an analysis of the corridor and guidance requirements for supercircular entry planetary atmospheres . 
5 : 0.040430893736985225 : 103 :  :  theory of mixing and chemical reaction in the opposed jet diffusion flame . 
==========================================================================================================
==========================================================================================================
Query: what theoretical and experimental guides do we have as to turbulent couette flow behaviour

Vector Representation of Query with Raw Count  - 
[behaviour:1, couette:1, do:1, experimental:1, flow:1, guide:1, theoretical:1, to:1, turbulent:1, we:1, ]

Vector Representation of Query by Weight Schema W1 - 
[turbulent:0.248579354304765, theoretical:0.1857438967342298, behaviour:0.4075952307462263, couette:0.5122785371621799, experimental:0.1437110957566392, do:0.273579573891666, to:0.0, flow:0.0, guide:0.4568253824567415, we:0.35598649017959844, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.0930239807304996 : 798 :  :  interaction between shock waves and boundary layers, with a note on the effects of the interaction of the performance of supersonic intakes . 
2 : 0.06011014964529475 : 491 :  :  on the close relationship between turbulent plane-couette and pressure flows . 
3 : 0.055677069173808894 : 386 :  :  a generalised porous-wall ?couette type? flow . 
4 : 0.051961993405885774 : 257 :  :  on turbulen flow between parallel plates . 
5 : 0.04810762139879235 : 130 :  :  the behaviour of non-linear systems . 


Vector Representation of Query by Weight Schema W2 - 
[turbulent:0.5206860025643327, theoretical:0.490179204384339, behaviour:0.597888675029461, couette:0.6487127260528986, experimental:0.46977215674051803, do:0.532823682194293, to:0.4, flow:0.4, guide:0.6217900574761038, we:0.5728324807458046, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.050207811213712594 : 798 :  :  interaction between shock waves and boundary layers, with a note on the effects of the interaction of the performance of supersonic intakes . 
2 : 0.0343219012531016 : 121 :  :  a theory for base pressures in transonic and supersonic flow . 
3 : 0.03375019803354674 : 491 :  :  on the close relationship between turbulent plane-couette and pressure flows . 
4 : 0.032642933638695644 : 315 :  :  scale effects at high subsonic and transonic speeds and methods for fixing transition in model experiments . 
5 : 0.0322621904783966 : 257 :  :  on turbulen flow between parallel plates . 
==========================================================================================================
==========================================================================================================
Query: is it possible to relate the available pressure distributions for an ogive forebody at zero angle of attack to the lower surface pressures of an equivalent ogive forebody at angle of attack

Vector Representation of Query with Raw Count  - 
[angle:2, at:2, attack:2, available:1, distribution:1, equivalent:1, forebody:2, lower:1, ogive:2, possible:1, pressure:2, relate:1, surface:1, to:2, zero:1, ]

Vector Representation of Query by Weight Schema W1 - 
[equivalent:0.30976064439391104, surface:0.11892251361252484, lower:0.2525871087712568, available:0.2430456776896415, possible:0.2131663758962014, pressure:0.0861552073394121, distribution:0.11892251361252484, ogive:0.6419606382696457, forebody:0.6419606382696457, zero:0.20570215187949642, at:0.0, relate:0.28886165551122284, attack:0.31881215116344147, angle:0.22270798021423638, to:0.0, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.09676171344108224 : 492 :  :  prediction of ogive-forebody pressures at angles of attack . 
2 : 0.06945152821902442 : 124 :  :  a summary of the supersonic pressure drag of bodies of revolution . 
3 : 0.0672118026162268 : 57 :  :  applicability of the hypersonic similarity rule to pressure distributions which include the effects of rotation for bodies of revolution at zero angle of attack . 
4 : 0.06365020613462824 : 373 :  :  the generalized expansion method and its application to bodies travelling at high supersonic airspeeds . 
5 : 0.059149612964306625 : 1040 :  :  on transverse vibrations of thin, shallow elastic shells . 


Vector Representation of Query by Weight Schema W2 - 
[equivalent:0.5642906081351052, surface:0.4630740296934283, lower:0.5339669530592359, available:0.5289063763099043, possible:0.5130590156101567, pressure:0.44071043224618867, distribution:0.4630740296934283, ogive:0.7033420251202981, forebody:0.7033420251202981, zero:0.5091001463181568, at:0.4, relate:0.5532062187683935, attack:0.5506465004264884, angle:0.505234940744547, to:0.4, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.04976980749648799 : 492 :  :  prediction of ogive-forebody pressures at angles of attack . 
2 : 0.041348801570711684 : 57 :  :  applicability of the hypersonic similarity rule to pressure distributions which include the effects of rotation for bodies of revolution at zero angle of attack . 
3 : 0.040930600463023374 : 373 :  :  the generalized expansion method and its application to bodies travelling at high supersonic airspeeds . 
4 : 0.039374261182536396 : 124 :  :  a summary of the supersonic pressure drag of bodies of revolution . 
5 : 0.03912282086837073 : 232 :  :  accuracy of approximate methods for predicting pressure on pointed non-lifting bodies of revolution in supersonic flow . 
==========================================================================================================
==========================================================================================================
Query: what methods -dash exact or approximate -dash are presently available for predicting body pressures at angle of attack

Vector Representation of Query with Raw Count  - 
[angle:1, approximate:1, at:1, attack:1, available:1, body:1, dash:2, exact:1, method:1, predict:1, presently:1, pressure:1, ]

Vector Representation of Query by Weight Schema W1 - 
[at:0.0, method:0.09424386228367657, presently:0.5025196342446007, attack:0.22003279637648082, approximate:0.16692885196679202, available:0.2430456776896415, exact:0.22003279637648082, angle:0.15370511908993897, predict:0.19752601954203555, pressure:0.05946125680626242, body:0.13806476273577312, dash:0.6268760067637584, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.05644001806524839 : 688 :  :  tables of aerodynamic coefficients obtained from developed newtonian expressions for complete and partial conic and spheric bodies at combined angles of attack and sideslip with some comparisons with hypersonic experimental data . 
2 : 0.054506570925818155 : 476 :  :  the blasius equation with three-point boundary conditions . 
3 : 0.05376121896371174 : 569 :  :  an experimental investigation of leading edge shock wave boundary layer interaction at mach 5.8 . 
4 : 0.05286132832426833 : 556 :  :  numerical comparison between exact and approximate theories of hypersonic inviscid flow past slender blunt nosed bodies . 
5 : 0.0528204959672095 : 443 :  :  calculated and measured pressure distributions over the midspan section of the naca 4412 airfoil . 


Vector Representation of Query by Weight Schema W2 - 
[at:0.4, method:0.4538631313757848, presently:0.6872047093820816, attack:0.5257551964767094, approximate:0.4954047347596193, available:0.5389077330925693, exact:0.5257551964767094, angle:0.48784698358129575, predict:0.5128919134139867, pressure:0.43398385220551094, body:0.47890806120847573, dash:0.7106506002649, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.04860047389870016 : 122 :  :  a simplified approximate method for the calculation of the pressure around conical bodies of arbitrary shape in supersonic and hypersonic flow . 
2 : 0.048505649310595464 : 433 :  :  application of two dimensional vortex theory to the prediction of flow fields behind wings of wing-body combinations at subsonic and supersonic speeds . 
3 : 0.04294604291643365 : 124 :  :  a summary of the supersonic pressure drag of bodies of revolution . 
4 : 0.042199787018729985 : 292 :  :  rapid laminar boundary layer calculations by piece-wise application of similar solutions . 
5 : 0.041127383976899926 : 232 :  :  accuracy of approximate methods for predicting pressure on pointed non-lifting bodies of revolution in supersonic flow . 
==========================================================================================================
==========================================================================================================
Query: papers on internal /slip flow/ heat transfer studies

Vector Representation of Query with Raw Count  - 
[flow:1, heat:1, internal:1, papers:1, slip:1, study:1, transfer:1, ]

Vector Representation of Query by Weight Schema W1 - 
[heat:0.16684341539219427, internal:0.36856744140554065, study:0.16684341539219427, transfer:0.20172402601334635, slip:0.46987483903550864, papers:0.5354108567977349, flow:0.0, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.07624887938909927 : 270 :  :  on combined free and forced convection laminar magnetohydrodynamic flow and heat transfer in channels with transverse magnetic field . 
2 : 0.06666292163845565 : 306 :  :  second approximation to laminar compressible boundary layer on flat plate in slip flow . 
3 : 0.06629612968829549 : 22 :  :  on slip-flow heat transfer to a flat plate . 
4 : 0.06582347421778367 : 1215 :  :  the effect of slip particularly for highly cooled walls . 
5 : 0.06550897204590514 : 21 :  :  on heat transfer in slip flow . 


Vector Representation of Query by Weight Schema W2 - 
[heat:0.48321213727439255, internal:0.5838207666572638, study:0.48321213727439255, transfer:0.5006086293828713, slip:0.6343472142169724, papers:0.6670329039316565, flow:0.4, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.05433455406099383 : 270 :  :  on combined free and forced convection laminar magnetohydrodynamic flow and heat transfer in channels with transverse magnetic field . 
2 : 0.05116689350629149 : 45 :  :  an investigation of separated flows, part ii: flow in the cavity and heat transfer . 
3 : 0.04448257227497149 : 306 :  :  second approximation to laminar compressible boundary layer on flat plate in slip flow . 
4 : 0.0444783293861992 : 571 :  :  heat transfer to flat plate in high temperature rarefied ultra-high mach number flow . 
5 : 0.04415461258956719 : 21 :  :  on heat transfer in slip flow . 
==========================================================================================================
==========================================================================================================
Query: are real-gas transport properties for air available over a wide range of enthalpies and densities

Vector Representation of Query with Raw Count  - 
[air:1, available:1, density:1, enthalpy:1, over:1, property:1, range:1, realgas:1, transport:1, wide:1, ]

Vector Representation of Query by Weight Schema W1 - 
[over:0.16684341539219427, realgas:0.5354108567977349, density:0.2937068818903161, wide:0.349072885987356, available:0.2937068818903161, property:0.25759944461254947, range:0.1437110957566392, air:0.20172402601334635, transport:0.4295007237251668, enthalpy:0.3899072268819348, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.08187806590922503 : 493 :  :  real-gas laminar boundary layer skin friction and heat transfer . 
2 : 0.06869587924294447 : 302 :  :  approximations for the thermodynamic and transport properties of high temperature air . 
3 : 0.059474513549035664 : 1264 :  :  boundary layer transition and heat transfer in shock tubes . 
4 : 0.05909561324561138 : 1143 :  :  a one-foot hypervelocity shock tunnel in which high-enthalpy real gas flows can be generated with flow times of about 180 milliseconds . 
5 : 0.05288178898829671 : 1009 :  :  free-flight measurements of the static and dynamic 


Vector Representation of Query by Weight Schema W2 - 
[over:0.48100296548834676, realgas:0.6599435348007274, density:0.542595549015381, wide:0.5694759057172494, available:0.542595549015381, property:0.5250652827545981, range:0.46977215674051803, air:0.49793760382403396, transport:0.6085238558521939, enthalpy:0.5893010974903117, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.05980785682408323 : 302 :  :  approximations for the thermodynamic and transport properties of high temperature air . 
2 : 0.055229121461039365 : 493 :  :  real-gas laminar boundary layer skin friction and heat transfer . 
3 : 0.04897373513728201 : 1264 :  :  boundary layer transition and heat transfer in shock tubes . 
4 : 0.04717883190172233 : 1010 :  :  free-flight measurements of the static and dynamic 
5 : 0.0423040484532704 : 949 :  :  charts for equilibrium flow properties of air in hypervelocity nozzles . 
==========================================================================================================
==========================================================================================================
Query: is it possible to find an analytical,  similar solution of the strong blast wave problem in the newtonian approximation

Vector Representation of Query with Raw Count  - 
[analytical:1, approximation:1, blast:1, find:1, newtonian:1, possible:1, problem:1, similar:1, solution:1, strong:1, to:1, wave:1, ]

Vector Representation of Query by Weight Schema W1 - 
[similar:0.25759944461254947, approximation:0.22777669771182044, strong:0.3714877934684596, problem:0.1437110957566392, analytical:0.3156123748692566, solution:0.11388834885591022, find:0.1437110957566392, newtonian:0.36246770316067517, possible:0.25759944461254947, to:0.0, wave:0.20172402601334635, blast:0.5122785371621799, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.10145255617208795 : 495 :  :  on similar solutions for strong blast waves and their application to steady hypersonic flow . 
2 : 0.05621131761766164 : 262 :  :  the formation of a blast wave by a very intense explosion . 
3 : 0.053314866021771344 : 25 :  :  inviscid hypersonic flow over blunt-nosed slender bodies . 
4 : 0.05287887112372899 : 1327 :  :  on the propagation and structure of the blast wave . 
5 : 0.052042567189572765 : 1157 :  :  hypersonic shock tunnel . 


Vector Representation of Query by Weight Schema W2 - 
[similar:0.5228902343588658, approximation:0.5086630128624529, strong:0.5772217407900923, problem:0.4685587279276395, analytical:0.5505658475800598, solution:0.45433150643122644, find:0.4685587279276395, newtonian:0.5729186219944403, possible:0.5228902343588658, to:0.4, wave:0.49623434114883336, blast:0.6443872873389352, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.05681429289087246 : 495 :  :  on similar solutions for strong blast waves and their application to steady hypersonic flow . 
2 : 0.04390773975875883 : 72 :  :  boundary layer behind shock or thin expansion wave moving into stationary fluid . 
3 : 0.03998893523977343 : 262 :  :  the formation of a blast wave by a very intense explosion . 
4 : 0.03722625841889138 : 160 :  :  approximate analytical solutions for hypersonic flow past slender power-law bodies . 
5 : 0.0369475689581783 : 1147 :  :  heat transfer to bodies traveling at high speed in the upper atmosphere . 
==========================================================================================================
==========================================================================================================
Query: how can the aerodynamic performance of channel flow ground effect machines be calculated

Vector Representation of Query with Raw Count  - 
[aerodynamic:1, calculate:1, can:1, channel:1, effect:1, flow:1, ground:1, machine:1, performance:1, ]

Vector Representation of Query by Weight Schema W1 - 
[can:0.1437110957566392, performance:0.35598649017959844, machine:0.44477323494900156, effect:0.0718555478783196, channel:0.4629612348432662, ground:0.4115828624660722, calculate:0.20172402601334635, aerodynamic:0.2155666436349588, flow:0.0, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.0931419522438668 : 624 :  :  cruise performance of channel-flow ground effect machines . 
2 : 0.07663420418287549 : 966 :  :  on fully developed channel flows,. some solutions and limitations, and effects of compressibility, variable properties, and body forces . 
3 : 0.06072274584229364 : 650 :  :  some design problems of hovercraft . 
4 : 0.059465441004468364 : 1232 :  :  the curtain jet . 
5 : 0.05487898554365003 : 506 :  :  a note on havelock's shallow-water wave-resistance curves . 


Vector Representation of Query by Weight Schema W2 - 
[can:0.47039512242570125, performance:0.5743756278953207, machine:0.6178667288079854, effect:0.43519756121285064, channel:0.6267758980860734, ground:0.6016088308218274, calculate:0.49881204671532, aerodynamic:0.5055926836385518, flow:0.4, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.05357087945763131 : 624 :  :  cruise performance of channel-flow ground effect machines . 
2 : 0.04318887086944585 : 966 :  :  on fully developed channel flows,. some solutions and limitations, and effects of compressibility, variable properties, and body forces . 
3 : 0.03988773971167898 : 792 :  :  some low speed problems of high speed aircraft . 
4 : 0.038058047097704766 : 1289 :  :  numerical technique to lifting surface theory for calculation of unsteady aerodynamic forces due to continuous sinusoidal gusts on several wing planforms at sobsonic speeds . 
5 : 0.03647399966820525 : 798 :  :  interaction between shock waves and boundary layers, with a note on the effects of the interaction of the performance of supersonic intakes . 
==========================================================================================================
==========================================================================================================
Query: what is the basic mechanism of the transonic aileron buzz

Vector Representation of Query with Raw Count  - 
[aileron:1, basic:1, buzz:1, mechanism:1, transonic:1, ]

Vector Representation of Query by Weight Schema W1 - 
[transonic:0.3204349021830846, aileron:0.5228299055717928, basic:0.3743281257338242, mechanism:0.4013105403691887, buzz:0.7509775004326937, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.2150789796540058 : 496 :  :  a theory of transonic aileron buzz, neglecting viscous effects . 
2 : 0.1156652439900145 : 903 :  :  two dimensional transonic unsteady flow with shock waves . 
3 : 0.11511946890654183 : 520 :  :  wing-tail interference as a cause of 'magnus' effects on a finned missile . 
4 : 0.07136336917462471 : 199 :  :  measurement of two dimensional derivatives on a wing-aileron-tab system . 
5 : 0.06908425264142573 : 643 :  :  an investigation of wing-aileron flutter using ground launched rocket models . 


Vector Representation of Query by Weight Schema W2 - 
[transonic:0.562774492088971, aileron:0.6655870872635101, basic:0.5901511668230384, mechanism:0.6038576913235936, buzz:0.7814814814814814, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.14919930901215103 : 496 :  :  a theory of transonic aileron buzz, neglecting viscous effects . 
2 : 0.11663888178202321 : 903 :  :  two dimensional transonic unsteady flow with shock waves . 
3 : 0.11559089939166897 : 520 :  :  wing-tail interference as a cause of 'magnus' effects on a finned missile . 
4 : 0.06886909428110398 : 797 :  :  a study of the effect of leading-edge modifications on the flow over a 50degree sweptback wing at transonic speeds . 
5 : 0.06878596287494333 : 38 :  :  on the prediction of mixed subsonic/supersonic pressure distributions . 
==========================================================================================================
==========================================================================================================
Query: papers on shock-sound wave interaction

Vector Representation of Query with Raw Count  - 
[interaction:1, papers:1, shocksound:1, wave:1, ]

Vector Representation of Query by Weight Schema W1 - 
[interaction:0.2937068818903161, papers:0.5354108567977349, wave:0.20172402601334635, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.12397792232790934 : 739 :  :  the buckling of thin cylindrical shells under axial compression . 
2 : 0.11538409238578395 : 46 :  :  some comments on the inversion of certain large matrices . 
3 : 0.11171527639793963 : 388 :  :  the pressure gradient induced by shear flow past a flat plate . 
4 : 0.10133231082134389 : 875 :  :  models for aeroelastic investigation . 
5 : 0.09733700777979609 : 195 :  :  correlation of theoretical and photo-thermoelastic results on thermal stresses in idealized wing structure . 


Vector Representation of Query by Weight Schema W2 - 
[interaction:0.5505915611097013, papers:0.6745198077802075, wave:0.503429432075849, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.08243341732398234 : 739 :  :  the buckling of thin cylindrical shells under axial compression . 
2 : 0.06740412759875848 : 798 :  :  interaction between shock waves and boundary layers, with a note on the effects of the interaction of the performance of supersonic intakes . 
3 : 0.06633183127721083 : 170 :  :  the interaction of a reflected shock wave with the boundary layer in a shock tube . 
4 : 0.06632231241860939 : 1364 :  :  an experimental investigation of the interaction between shock waves and  boundary layers . 
5 : 0.06600773013576972 : 256 :  :  an experimental study of the glancing interaction between a shock wave and a turbulent boundary layer . 
==========================================================================================================
==========================================================================================================
Query: material properties of photoelastic materials

Vector Representation of Query with Raw Count  - 
[material:2, photoelastic:1, property:1, ]

Vector Representation of Query by Weight Schema W1 - 
[material:0.3784210182600163, photoelastic:0.6214421478571255, property:0.2131663758962014, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.2234506225521334 : 462 :  :  photo-thermoelasticity . 
2 : 0.07176681675501526 : 1025 :  :  note on creep buckling of columns . 
3 : 0.06959651906165473 : 82 :  :  theoretical investigation of the ablation of a glass-type heat protection shield of varied material properties at the stagnation point of a re-entering irbm . 
4 : 0.0690057605859785 : 463 :  :  physical properties of plastics for photo-thermoelastic investigation . 
5 : 0.06634830116599767 : 542 :  :  biot's variational principle in heat conduction . 


Vector Representation of Query by Weight Schema W2 - 
[material:0.5971355499625071, photoelastic:0.7850467289719626, property:0.5320782892642016, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.1552790971951452 : 462 :  :  photo-thermoelasticity . 
2 : 0.08137601418602458 : 82 :  :  theoretical investigation of the ablation of a glass-type heat protection shield of varied material properties at the stagnation point of a re-entering irbm . 
3 : 0.08119758454928586 : 1025 :  :  note on creep buckling of columns . 
4 : 0.08048021405025242 : 463 :  :  physical properties of plastics for photo-thermoelastic investigation . 
5 : 0.07985881312515285 : 542 :  :  biot's variational principle in heat conduction . 
==========================================================================================================
==========================================================================================================
Query: can the transverse potential flow about a body of revolution be calculated efficiently by an electronic computer

Vector Representation of Query with Raw Count  - 
[about:1, body:1, calculate:1, can:1, computer:1, efficiently:1, electronic:1, flow:1, potential:1, revolution:1, transverse:1, ]

Vector Representation of Query by Weight Schema W1 - 
[can:0.1437110957566392, computer:0.39689857059231937, transverse:0.329454992490869, about:0.20172402601334635, electronic:0.5228299055717928, calculate:0.20172402601334635, body:0.16684341539219427, potential:0.33368683078438854, revolution:0.29963224559014, flow:0.0, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.10958455551737459 : 498 :  :  calculation of potential flow about bodies of revolution having axes perpendicular to the free-stream direction . 
2 : 0.0847218374252403 : 869 :  :  the calculation of transient temperature in turbine blades and tapered discs using biot's variational method . 
3 : 0.06275707823731648 : 93 :  :  the supersonic blunt body problem - review and extensions . 
4 : 0.060025054394007286 : 1280 :  :  wings with minimum drag due to lift in supersonic flow . 
5 : 0.05834391729482056 : 1286 :  :  equilibrium real-gas performance charts for a shypersonic shock-tube wind-tunnel employing nitrogen . 


Vector Representation of Query by Weight Schema W2 - 
[can:0.4691601202778819, computer:0.5910051046216178, transverse:0.5585482790097267, about:0.4970785020361038, electronic:0.6516088195127991, calculate:0.4970785020361038, body:0.4802924131595016, potential:0.5605848263190032, revolution:0.5441962573247486, flow:0.4, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.06929428870590036 : 498 :  :  calculation of potential flow about bodies of revolution having axes perpendicular to the free-stream direction . 
2 : 0.05124576999836623 : 869 :  :  the calculation of transient temperature in turbine blades and tapered discs using biot's variational method . 
3 : 0.04471437038610878 : 927 :  :  investigation of normal force distributions and wake vortex characteristics of bodies of revolution at supersonic speeds . 
4 : 0.04282784980765746 : 1255 :  :  the flow about a charged body moving in the lower atmosphere . 
5 : 0.040056966712602095 : 106 :  :  the transverse potential flow past a body of revolution . 
==========================================================================================================
==========================================================================================================
Query: can the three-dimensional problem of a transverse potential flow about a body of revolution be reduced to a two-dimensional problem

Vector Representation of Query with Raw Count  - 
[about:1, body:1, can:1, flow:1, potential:1, problem:2, reduce:1, revolution:1, threedimensional:1, to:1, transverse:1, twodimensional:1, ]

Vector Representation of Query by Weight Schema W1 - 
[reduce:0.20570215187949642, can:0.11892251361252484, problem:0.1723104146788242, transverse:0.2726276327024638, twodimensional:0.17838377041878725, about:0.16692885196679202, to:0.0, body:0.13806476273577312, potential:0.27612952547154623, revolution:0.2479489813736155, flow:0.0, threedimensional:0.27949405318274323, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.05629393070238249 : 1108 :  :  a study of second-order supersonic flow theory . 
2 : 0.05386252775102782 : 106 :  :  the transverse potential flow past a body of revolution . 
3 : 0.05247718024193764 : 1281 :  :  turbulent heat transfer on blunt-nosed bodies in two-dimensional and general three-dimensional hypersonic flow . 
4 : 0.04925245829631933 : 373 :  :  the generalized expansion method and its application to bodies travelling at high supersonic airspeeds . 
5 : 0.047482765003686866 : 916 :  :  the flow around oscillating low aspect ratio wings at transonic speeds . 


Vector Representation of Query by Weight Schema W2 - 
[reduce:0.5175648128428414, can:0.46796770441102187, problem:0.4853890293683596, transverse:0.5558146879923176, twodimensional:0.5019515566165328, about:0.4954047347596193, to:0.4, body:0.47890806120847573, potential:0.5578161224169513, revolution:0.5417101149570805, flow:0.4, threedimensional:0.5597390486822204, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.04252130369353189 : 1108 :  :  a study of second-order supersonic flow theory . 
2 : 0.04087915864071116 : 373 :  :  the generalized expansion method and its application to bodies travelling at high supersonic airspeeds . 
3 : 0.0381708994834559 : 1248 :  :  an analytic extension of the shock-expansion method . 
4 : 0.03694138993031825 : 1281 :  :  turbulent heat transfer on blunt-nosed bodies in two-dimensional and general three-dimensional hypersonic flow . 
5 : 0.03572309732404252 : 801 :  :  experimental study of the equivalence of transonic flow about slender cone-cylinders of circular and elliptic cross section . 
==========================================================================================================
==========================================================================================================
Query: are experimental pressure distributions on bodies of revolution at angle of attack available

Vector Representation of Query with Raw Count  - 
[angle:1, at:1, attack:1, available:1, body:1, distribution:1, experimental:1, pressure:1, revolution:1, ]

Vector Representation of Query by Weight Schema W1 - 
[at:0.0, attack:0.26589712333772275, available:0.2937068818903161, angle:0.1857438967342298, experimental:0.1437110957566392, pressure:0.0718555478783196, body:0.16684341539219427, distribution:0.1437110957566392, revolution:0.29963224559014, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.06987701891701523 : 124 :  :  a summary of the supersonic pressure drag of bodies of revolution . 
2 : 0.06975973978865756 : 197 :  :  pressure distributions on three bodies of revolution to determine the effect of reynolds number up to and including the transonic speed range . 
3 : 0.06711359964407522 : 927 :  :  investigation of normal force distributions and wake vortex characteristics of bodies of revolution at supersonic speeds . 
4 : 0.06705446341303803 : 234 :  :  a second order shock-expansion method applicable to bodies of revolution near zero lift . 
5 : 0.0666398274622637 : 498 :  :  calculation of potential flow about bodies of revolution having axes perpendicular to the free-stream direction . 


Vector Representation of Query by Weight Schema W2 - 
[at:0.4, attack:0.5302464534937348, available:0.5438687235601611, angle:0.49098437585205634, experimental:0.47039512242570125, pressure:0.43519756121285064, body:0.48172620625163554, distribution:0.47039512242570125, revolution:0.546771190491262, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.055589975754367395 : 197 :  :  pressure distributions on three bodies of revolution to determine the effect of reynolds number up to and including the transonic speed range . 
2 : 0.055220092548031695 : 234 :  :  a second order shock-expansion method applicable to bodies of revolution near zero lift . 
3 : 0.05492353551472839 : 927 :  :  investigation of normal force distributions and wake vortex characteristics of bodies of revolution at supersonic speeds . 
4 : 0.05443282017616943 : 498 :  :  calculation of potential flow about bodies of revolution having axes perpendicular to the free-stream direction . 
5 : 0.05416456030554456 : 1352 :  :  aerodynamic investigation of a parabolic body of revolution at mach number of 1. 92 and some effects of an annular supersonic jet exhausting from the base . 
==========================================================================================================
==========================================================================================================
Query: does there exist a good basic treatment of the dynamics of re-entry combining consideration of realistic effects with relative simplicity of results

Vector Representation of Query with Raw Count  - 
[basic:1, combine:1, consideration:1, do:1, dynamics:1, effect:1, exist:1, good:1, realistic:1, reentry:1, relative:1, result:1, simplicity:1, treatment:1, ]

Vector Representation of Query by Weight Schema W1 - 
[treatment:0.3655624297686357, simplicity:0.4927839817439952, do:0.273579573891666, good:0.23869896327051393, exist:0.26589712333772275, result:0.0718555478783196, dynamics:0.4927839817439952, realistic:0.6791219525543741, effect:0.0718555478783196, consideration:0.2807317642481045, basic:0.3743281257338242, reentry:0.4115828624660722, combine:0.38241005902715314, relative:0.36856744140554065, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.06368091881607796 : 82 :  :  theoretical investigation of the ablation of a glass-type heat protection shield of varied material properties at the stagnation point of a re-entering irbm . 
2 : 0.045787727858349614 : 237 :  :  a compressor routine test code . 
3 : 0.0439336331703504 : 1119 :  :  plastic stability theory of thin shells . 
4 : 0.038739089244016325 : 706 :  :  on som reciprocal relations in the theory of nonstationary flows . 
5 : 0.0375425310081984 : 1279 :  :  sublimation in a hypersonic environment . 


Vector Representation of Query by Weight Schema W2 - 
[treatment:0.5714138794408318, simplicity:0.6310686415190403, do:0.528282701606454, good:0.5119270252651492, exist:0.5246803657375922, result:0.4336933919302502, dynamics:0.6310686415190403, realistic:0.718443360206502, effect:0.4336933919302502, consideration:0.5316363960664463, basic:0.575524153990497, reentry:0.5929930688208945, combine:0.5793138091256494, relative:0.5728229430111027, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.03634760218707179 : 82 :  :  theoretical investigation of the ablation of a glass-type heat protection shield of varied material properties at the stagnation point of a re-entering irbm . 
2 : 0.03497925495240515 : 927 :  :  investigation of normal force distributions and wake vortex characteristics of bodies of revolution at supersonic speeds . 
3 : 0.03396413239460608 : 1119 :  :  plastic stability theory of thin shells . 
4 : 0.030682236546166166 : 44 :  :  tip-bluntness effects on cone pressures at m=6.85. 
5 : 0.030543760349774095 : 395 :  :  new methods in heat flow analysis with application to flight structures . 
==========================================================================================================
==========================================================================================================
Query: has anyone formally determined the influence of joule heating,  produced by the induced current,  in magnetohydrodynamic free convection flows under general conditions

Vector Representation of Query with Raw Count  - 
[anyone:1, condition:1, convection:1, current:1, determine:1, flow:1, formally:1, free:1, general:1, heating:1, induce:1, influence:1, joule:1, magnetohydrodynamic:1, produce:1, under:1, ]

Vector Representation of Query by Weight Schema W1 - 
[joule:0.7509775004326937, determine:0.16684341539219427, produce:0.29963224559014, influence:0.26589712333772275, convection:0.4295007237251668, general:0.1857438967342298, condition:0.1437110957566392, current:0.4404229892838603, induce:0.33368683078438854, magnetohydrodynamic:0.4209284338656756, free:0.26589712333772275, heating:0.3052371588258767, under:0.20172402601334635, formally:0.6369409519195428, flow:0.0, ]

Top Five document by W1
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.08601808935740442 : 500 :  :  joule heating in magnetohydrodynamic free-convection flows . 
2 : 0.043705534678477334 : 1371 :  :  axisymmetric free mixing with swirl . 
3 : 0.041808831231078064 : 270 :  :  on combined free and forced convection laminar magnetohydrodynamic flow and heat transfer in channels with transverse magnetic field . 
4 : 0.039373261509463166 : 458 :  :  a new series for calculation of steady laminar boundary layer flows . 
5 : 0.036430604841318104 : 44 :  :  tip-bluntness effects on cone pressures at m=6.85. 


Vector Representation of Query by Weight Schema W2 - 
[joule:0.746218487394958, determine:0.47691878235448054, produce:0.5381375910505996, influence:0.522584897405868, convection:0.5980100479941001, general:0.4856323537431118, condition:0.46625423287124823, current:0.6030454721692293, induce:0.5538375647089611, magnetohydrodynamic:0.5940580185032642, free:0.522584897405868, heating:0.5407215893479369, under:0.4929995733791247, formally:0.6936449265210083, flow:0.4, ]

Top Five document by W2
Rank : 	 Weight       :  DocId :  :  Title
1 : 0.037932364946875366 : 500 :  :  joule heating in magnetohydrodynamic free-convection flows . 
2 : 0.033607736998730015 : 44 :  :  tip-bluntness effects on cone pressures at m=6.85. 
3 : 0.031180146451342067 : 270 :  :  on combined free and forced convection laminar magnetohydrodynamic flow and heat transfer in channels with transverse magnetic field . 
4 : 0.030364029921315525 : 123 :  :  the downstream influence of mass transfer at the nose of a slender cone . 
5 : 0.028617919807408323 : 458 :  :  a new series for calculation of steady laminar boundary layer flows . 
==========================================================================================================
