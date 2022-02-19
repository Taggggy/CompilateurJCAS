pragma Ada_95;
pragma Warnings (Off);
with System;
package ada_main is

   gnat_argc : Integer;
   gnat_argv : System.Address;
   gnat_envp : System.Address;

   pragma Import (C, gnat_argc);
   pragma Import (C, gnat_argv);
   pragma Import (C, gnat_envp);

   gnat_exit_status : Integer;
   pragma Import (C, gnat_exit_status);

   GNAT_Version : constant String :=
                    "GNAT Version: 6.3.0" & ASCII.NUL;
   pragma Export (C, GNAT_Version, "__gnat_version");

   Ada_Main_Program_Name : constant String := "_ada_ima" & ASCII.NUL;
   pragma Export (C, Ada_Main_Program_Name, "__gnat_ada_main_program_name");

   procedure adainit;
   pragma Export (C, adainit, "adainit");

   procedure adafinal;
   pragma Export (C, adafinal, "adafinal");

   function main
     (argc : Integer;
      argv : System.Address;
      envp : System.Address)
      return Integer;
   pragma Export (C, main, "main");

   type Version_32 is mod 2 ** 32;
   u00001 : constant Version_32 := 16#d8ec1bbc#;
   pragma Export (C, u00001, "imaB");
   u00002 : constant Version_32 := 16#b6df930e#;
   pragma Export (C, u00002, "system__standard_libraryB");
   u00003 : constant Version_32 := 16#937076cc#;
   pragma Export (C, u00003, "system__standard_libraryS");
   u00004 : constant Version_32 := 16#3ffc8e18#;
   pragma Export (C, u00004, "adaS");
   u00005 : constant Version_32 := 16#e51537a7#;
   pragma Export (C, u00005, "ada__command_lineB");
   u00006 : constant Version_32 := 16#d59e21a4#;
   pragma Export (C, u00006, "ada__command_lineS");
   u00007 : constant Version_32 := 16#6326c08a#;
   pragma Export (C, u00007, "systemS");
   u00008 : constant Version_32 := 16#0f0cb66d#;
   pragma Export (C, u00008, "system__secondary_stackB");
   u00009 : constant Version_32 := 16#c8470fe3#;
   pragma Export (C, u00009, "system__secondary_stackS");
   u00010 : constant Version_32 := 16#b01dad17#;
   pragma Export (C, u00010, "system__parametersB");
   u00011 : constant Version_32 := 16#1d0ccdf5#;
   pragma Export (C, u00011, "system__parametersS");
   u00012 : constant Version_32 := 16#465d427a#;
   pragma Export (C, u00012, "system__soft_linksB");
   u00013 : constant Version_32 := 16#fda218df#;
   pragma Export (C, u00013, "system__soft_linksS");
   u00014 : constant Version_32 := 16#e7214354#;
   pragma Export (C, u00014, "ada__exceptionsB");
   u00015 : constant Version_32 := 16#020f9e08#;
   pragma Export (C, u00015, "ada__exceptionsS");
   u00016 : constant Version_32 := 16#e947e6a9#;
   pragma Export (C, u00016, "ada__exceptions__last_chance_handlerB");
   u00017 : constant Version_32 := 16#41e5552e#;
   pragma Export (C, u00017, "ada__exceptions__last_chance_handlerS");
   u00018 : constant Version_32 := 16#87a448ff#;
   pragma Export (C, u00018, "system__exception_tableB");
   u00019 : constant Version_32 := 16#3e88a9c8#;
   pragma Export (C, u00019, "system__exception_tableS");
   u00020 : constant Version_32 := 16#ce4af020#;
   pragma Export (C, u00020, "system__exceptionsB");
   u00021 : constant Version_32 := 16#0b45ad7c#;
   pragma Export (C, u00021, "system__exceptionsS");
   u00022 : constant Version_32 := 16#4c9e814d#;
   pragma Export (C, u00022, "system__exceptions__machineS");
   u00023 : constant Version_32 := 16#aa0563fc#;
   pragma Export (C, u00023, "system__exceptions_debugB");
   u00024 : constant Version_32 := 16#1dac394e#;
   pragma Export (C, u00024, "system__exceptions_debugS");
   u00025 : constant Version_32 := 16#570325c8#;
   pragma Export (C, u00025, "system__img_intB");
   u00026 : constant Version_32 := 16#61fd2048#;
   pragma Export (C, u00026, "system__img_intS");
   u00027 : constant Version_32 := 16#39a03df9#;
   pragma Export (C, u00027, "system__storage_elementsB");
   u00028 : constant Version_32 := 16#4ee58a8e#;
   pragma Export (C, u00028, "system__storage_elementsS");
   u00029 : constant Version_32 := 16#39df8c17#;
   pragma Export (C, u00029, "system__tracebackB");
   u00030 : constant Version_32 := 16#3d041e4e#;
   pragma Export (C, u00030, "system__tracebackS");
   u00031 : constant Version_32 := 16#9ed49525#;
   pragma Export (C, u00031, "system__traceback_entriesB");
   u00032 : constant Version_32 := 16#637d36fa#;
   pragma Export (C, u00032, "system__traceback_entriesS");
   u00033 : constant Version_32 := 16#6fd210f2#;
   pragma Export (C, u00033, "system__traceback__symbolicB");
   u00034 : constant Version_32 := 16#dd19f67a#;
   pragma Export (C, u00034, "system__traceback__symbolicS");
   u00035 : constant Version_32 := 16#701f9d88#;
   pragma Export (C, u00035, "ada__exceptions__tracebackB");
   u00036 : constant Version_32 := 16#20245e75#;
   pragma Export (C, u00036, "ada__exceptions__tracebackS");
   u00037 : constant Version_32 := 16#57a37a42#;
   pragma Export (C, u00037, "system__address_imageB");
   u00038 : constant Version_32 := 16#c2ca5db0#;
   pragma Export (C, u00038, "system__address_imageS");
   u00039 : constant Version_32 := 16#8c33a517#;
   pragma Export (C, u00039, "system__wch_conB");
   u00040 : constant Version_32 := 16#785be258#;
   pragma Export (C, u00040, "system__wch_conS");
   u00041 : constant Version_32 := 16#9721e840#;
   pragma Export (C, u00041, "system__wch_stwB");
   u00042 : constant Version_32 := 16#554ace59#;
   pragma Export (C, u00042, "system__wch_stwS");
   u00043 : constant Version_32 := 16#b96cfbef#;
   pragma Export (C, u00043, "system__wch_cnvB");
   u00044 : constant Version_32 := 16#77ec58ab#;
   pragma Export (C, u00044, "system__wch_cnvS");
   u00045 : constant Version_32 := 16#4be8ce1b#;
   pragma Export (C, u00045, "interfacesS");
   u00046 : constant Version_32 := 16#ece6fdb6#;
   pragma Export (C, u00046, "system__wch_jisB");
   u00047 : constant Version_32 := 16#f79c418a#;
   pragma Export (C, u00047, "system__wch_jisS");
   u00048 : constant Version_32 := 16#41837d1e#;
   pragma Export (C, u00048, "system__stack_checkingB");
   u00049 : constant Version_32 := 16#ed99ab62#;
   pragma Export (C, u00049, "system__stack_checkingS");
   u00050 : constant Version_32 := 16#f64b89a4#;
   pragma Export (C, u00050, "ada__integer_text_ioB");
   u00051 : constant Version_32 := 16#f1daf268#;
   pragma Export (C, u00051, "ada__integer_text_ioS");
   u00052 : constant Version_32 := 16#c1a106e2#;
   pragma Export (C, u00052, "ada__text_ioB");
   u00053 : constant Version_32 := 16#8d734ca7#;
   pragma Export (C, u00053, "ada__text_ioS");
   u00054 : constant Version_32 := 16#10558b11#;
   pragma Export (C, u00054, "ada__streamsB");
   u00055 : constant Version_32 := 16#2e6701ab#;
   pragma Export (C, u00055, "ada__streamsS");
   u00056 : constant Version_32 := 16#db5c917c#;
   pragma Export (C, u00056, "ada__io_exceptionsS");
   u00057 : constant Version_32 := 16#920eada5#;
   pragma Export (C, u00057, "ada__tagsB");
   u00058 : constant Version_32 := 16#13ca27f3#;
   pragma Export (C, u00058, "ada__tagsS");
   u00059 : constant Version_32 := 16#c3335bfd#;
   pragma Export (C, u00059, "system__htableB");
   u00060 : constant Version_32 := 16#e7e47360#;
   pragma Export (C, u00060, "system__htableS");
   u00061 : constant Version_32 := 16#089f5cd0#;
   pragma Export (C, u00061, "system__string_hashB");
   u00062 : constant Version_32 := 16#45ba181e#;
   pragma Export (C, u00062, "system__string_hashS");
   u00063 : constant Version_32 := 16#fe7e644a#;
   pragma Export (C, u00063, "system__unsigned_typesS");
   u00064 : constant Version_32 := 16#06052bd0#;
   pragma Export (C, u00064, "system__val_lluB");
   u00065 : constant Version_32 := 16#848c3338#;
   pragma Export (C, u00065, "system__val_lluS");
   u00066 : constant Version_32 := 16#27b600b2#;
   pragma Export (C, u00066, "system__val_utilB");
   u00067 : constant Version_32 := 16#cf867674#;
   pragma Export (C, u00067, "system__val_utilS");
   u00068 : constant Version_32 := 16#d1060688#;
   pragma Export (C, u00068, "system__case_utilB");
   u00069 : constant Version_32 := 16#472fa95d#;
   pragma Export (C, u00069, "system__case_utilS");
   u00070 : constant Version_32 := 16#84a27f0d#;
   pragma Export (C, u00070, "interfaces__c_streamsB");
   u00071 : constant Version_32 := 16#a06e9ee4#;
   pragma Export (C, u00071, "interfaces__c_streamsS");
   u00072 : constant Version_32 := 16#13b71684#;
   pragma Export (C, u00072, "system__crtlS");
   u00073 : constant Version_32 := 16#f1dc49a7#;
   pragma Export (C, u00073, "system__file_ioB");
   u00074 : constant Version_32 := 16#c45721ef#;
   pragma Export (C, u00074, "system__file_ioS");
   u00075 : constant Version_32 := 16#cf417de3#;
   pragma Export (C, u00075, "ada__finalizationS");
   u00076 : constant Version_32 := 16#95817ed8#;
   pragma Export (C, u00076, "system__finalization_rootB");
   u00077 : constant Version_32 := 16#2cd4b31a#;
   pragma Export (C, u00077, "system__finalization_rootS");
   u00078 : constant Version_32 := 16#769e25e6#;
   pragma Export (C, u00078, "interfaces__cB");
   u00079 : constant Version_32 := 16#61e3d2ff#;
   pragma Export (C, u00079, "interfaces__cS");
   u00080 : constant Version_32 := 16#b97322e0#;
   pragma Export (C, u00080, "system__os_libB");
   u00081 : constant Version_32 := 16#dc0cac3f#;
   pragma Export (C, u00081, "system__os_libS");
   u00082 : constant Version_32 := 16#1a817b8e#;
   pragma Export (C, u00082, "system__stringsB");
   u00083 : constant Version_32 := 16#1d99d1ec#;
   pragma Export (C, u00083, "system__stringsS");
   u00084 : constant Version_32 := 16#9eb95a22#;
   pragma Export (C, u00084, "system__file_control_blockS");
   u00085 : constant Version_32 := 16#f6fdca1c#;
   pragma Export (C, u00085, "ada__text_io__integer_auxB");
   u00086 : constant Version_32 := 16#b9793d30#;
   pragma Export (C, u00086, "ada__text_io__integer_auxS");
   u00087 : constant Version_32 := 16#181dc502#;
   pragma Export (C, u00087, "ada__text_io__generic_auxB");
   u00088 : constant Version_32 := 16#a6c327d3#;
   pragma Export (C, u00088, "ada__text_io__generic_auxS");
   u00089 : constant Version_32 := 16#18d57884#;
   pragma Export (C, u00089, "system__img_biuB");
   u00090 : constant Version_32 := 16#385cec07#;
   pragma Export (C, u00090, "system__img_biuS");
   u00091 : constant Version_32 := 16#e7d8734f#;
   pragma Export (C, u00091, "system__img_llbB");
   u00092 : constant Version_32 := 16#799bfcf9#;
   pragma Export (C, u00092, "system__img_llbS");
   u00093 : constant Version_32 := 16#9777733a#;
   pragma Export (C, u00093, "system__img_lliB");
   u00094 : constant Version_32 := 16#7269955b#;
   pragma Export (C, u00094, "system__img_lliS");
   u00095 : constant Version_32 := 16#0e8808d4#;
   pragma Export (C, u00095, "system__img_llwB");
   u00096 : constant Version_32 := 16#d0f7df6f#;
   pragma Export (C, u00096, "system__img_llwS");
   u00097 : constant Version_32 := 16#428b07f8#;
   pragma Export (C, u00097, "system__img_wiuB");
   u00098 : constant Version_32 := 16#561d6b95#;
   pragma Export (C, u00098, "system__img_wiuS");
   u00099 : constant Version_32 := 16#7ebd8839#;
   pragma Export (C, u00099, "system__val_intB");
   u00100 : constant Version_32 := 16#2b83eab5#;
   pragma Export (C, u00100, "system__val_intS");
   u00101 : constant Version_32 := 16#b44f9ae7#;
   pragma Export (C, u00101, "system__val_unsB");
   u00102 : constant Version_32 := 16#eed68971#;
   pragma Export (C, u00102, "system__val_unsS");
   u00103 : constant Version_32 := 16#b3aa7b17#;
   pragma Export (C, u00103, "system__val_lliB");
   u00104 : constant Version_32 := 16#f902262a#;
   pragma Export (C, u00104, "system__val_lliS");
   u00105 : constant Version_32 := 16#b59b63c1#;
   pragma Export (C, u00105, "assembleurB");
   u00106 : constant Version_32 := 16#79c41007#;
   pragma Export (C, u00106, "assembleurS");
   u00107 : constant Version_32 := 16#bd463446#;
   pragma Export (C, u00107, "ma_detiqB");
   u00108 : constant Version_32 := 16#11d3f81e#;
   pragma Export (C, u00108, "ma_detiqS");
   u00109 : constant Version_32 := 16#c50dcf1a#;
   pragma Export (C, u00109, "mes_tablesB");
   u00110 : constant Version_32 := 16#e67d62e6#;
   pragma Export (C, u00110, "mes_tablesS");
   u00111 : constant Version_32 := 16#7dbbd31d#;
   pragma Export (C, u00111, "text_ioS");
   u00112 : constant Version_32 := 16#1c33b50d#;
   pragma Export (C, u00112, "types_baseB");
   u00113 : constant Version_32 := 16#447091ce#;
   pragma Export (C, u00113, "types_baseS");
   u00114 : constant Version_32 := 16#5b9edcc4#;
   pragma Export (C, u00114, "system__compare_array_unsigned_8B");
   u00115 : constant Version_32 := 16#ca25b107#;
   pragma Export (C, u00115, "system__compare_array_unsigned_8S");
   u00116 : constant Version_32 := 16#5f72f755#;
   pragma Export (C, u00116, "system__address_operationsB");
   u00117 : constant Version_32 := 16#702a7eb9#;
   pragma Export (C, u00117, "system__address_operationsS");
   u00118 : constant Version_32 := 16#fd83e873#;
   pragma Export (C, u00118, "system__concat_2B");
   u00119 : constant Version_32 := 16#6186175a#;
   pragma Export (C, u00119, "system__concat_2S");
   u00120 : constant Version_32 := 16#6abe5dbe#;
   pragma Export (C, u00120, "system__finalization_mastersB");
   u00121 : constant Version_32 := 16#38daf940#;
   pragma Export (C, u00121, "system__finalization_mastersS");
   u00122 : constant Version_32 := 16#7268f812#;
   pragma Export (C, u00122, "system__img_boolB");
   u00123 : constant Version_32 := 16#96ffb161#;
   pragma Export (C, u00123, "system__img_boolS");
   u00124 : constant Version_32 := 16#d7aac20c#;
   pragma Export (C, u00124, "system__ioB");
   u00125 : constant Version_32 := 16#fd6437c5#;
   pragma Export (C, u00125, "system__ioS");
   u00126 : constant Version_32 := 16#6d4d969a#;
   pragma Export (C, u00126, "system__storage_poolsB");
   u00127 : constant Version_32 := 16#40cb5e27#;
   pragma Export (C, u00127, "system__storage_poolsS");
   u00128 : constant Version_32 := 16#5a895de2#;
   pragma Export (C, u00128, "system__pool_globalB");
   u00129 : constant Version_32 := 16#7141203e#;
   pragma Export (C, u00129, "system__pool_globalS");
   u00130 : constant Version_32 := 16#58e7cff7#;
   pragma Export (C, u00130, "system__memoryB");
   u00131 : constant Version_32 := 16#3a5ba6be#;
   pragma Export (C, u00131, "system__memoryS");
   u00132 : constant Version_32 := 16#956644e6#;
   pragma Export (C, u00132, "pseudo_codeB");
   u00133 : constant Version_32 := 16#6837ff4f#;
   pragma Export (C, u00133, "pseudo_codeS");
   u00134 : constant Version_32 := 16#1384aef2#;
   pragma Export (C, u00134, "entier_esB");
   u00135 : constant Version_32 := 16#6f99cae8#;
   pragma Export (C, u00135, "entier_esS");
   u00136 : constant Version_32 := 16#5182b687#;
   pragma Export (C, u00136, "pseudo_code__tableB");
   u00137 : constant Version_32 := 16#6cd79f5c#;
   pragma Export (C, u00137, "pseudo_code__tableS");
   u00138 : constant Version_32 := 16#12c24a43#;
   pragma Export (C, u00138, "ada__charactersS");
   u00139 : constant Version_32 := 16#8f637df8#;
   pragma Export (C, u00139, "ada__characters__handlingB");
   u00140 : constant Version_32 := 16#3b3f6154#;
   pragma Export (C, u00140, "ada__characters__handlingS");
   u00141 : constant Version_32 := 16#4b7bb96a#;
   pragma Export (C, u00141, "ada__characters__latin_1S");
   u00142 : constant Version_32 := 16#af50e98f#;
   pragma Export (C, u00142, "ada__stringsS");
   u00143 : constant Version_32 := 16#e2ea8656#;
   pragma Export (C, u00143, "ada__strings__mapsB");
   u00144 : constant Version_32 := 16#1e526bec#;
   pragma Export (C, u00144, "ada__strings__mapsS");
   u00145 : constant Version_32 := 16#ad32e455#;
   pragma Export (C, u00145, "system__bit_opsB");
   u00146 : constant Version_32 := 16#0765e3a3#;
   pragma Export (C, u00146, "system__bit_opsS");
   u00147 : constant Version_32 := 16#92f05f13#;
   pragma Export (C, u00147, "ada__strings__maps__constantsS");
   u00148 : constant Version_32 := 16#d0432c8d#;
   pragma Export (C, u00148, "system__img_enum_newB");
   u00149 : constant Version_32 := 16#026ac64a#;
   pragma Export (C, u00149, "system__img_enum_newS");
   u00150 : constant Version_32 := 16#c7fe82f1#;
   pragma Export (C, u00150, "reel_esB");
   u00151 : constant Version_32 := 16#bbe3e6eb#;
   pragma Export (C, u00151, "reel_esS");
   u00152 : constant Version_32 := 16#d5f9759f#;
   pragma Export (C, u00152, "ada__text_io__float_auxB");
   u00153 : constant Version_32 := 16#f854caf5#;
   pragma Export (C, u00153, "ada__text_io__float_auxS");
   u00154 : constant Version_32 := 16#237a28d3#;
   pragma Export (C, u00154, "system__img_realB");
   u00155 : constant Version_32 := 16#a48e9168#;
   pragma Export (C, u00155, "system__img_realS");
   u00156 : constant Version_32 := 16#67b17b79#;
   pragma Export (C, u00156, "system__fat_llfS");
   u00157 : constant Version_32 := 16#1b28662b#;
   pragma Export (C, u00157, "system__float_controlB");
   u00158 : constant Version_32 := 16#83da83b6#;
   pragma Export (C, u00158, "system__float_controlS");
   u00159 : constant Version_32 := 16#f1f88835#;
   pragma Export (C, u00159, "system__img_lluB");
   u00160 : constant Version_32 := 16#b7b76489#;
   pragma Export (C, u00160, "system__img_lluS");
   u00161 : constant Version_32 := 16#eef535cd#;
   pragma Export (C, u00161, "system__img_unsB");
   u00162 : constant Version_32 := 16#618a58bd#;
   pragma Export (C, u00162, "system__img_unsS");
   u00163 : constant Version_32 := 16#3356a6fd#;
   pragma Export (C, u00163, "system__powten_tableS");
   u00164 : constant Version_32 := 16#faa9a7b2#;
   pragma Export (C, u00164, "system__val_realB");
   u00165 : constant Version_32 := 16#9d0fb79b#;
   pragma Export (C, u00165, "system__val_realS");
   u00166 : constant Version_32 := 16#6c05c057#;
   pragma Export (C, u00166, "system__exn_llfB");
   u00167 : constant Version_32 := 16#df587b56#;
   pragma Export (C, u00167, "system__exn_llfS");
   u00168 : constant Version_32 := 16#1d61d593#;
   pragma Export (C, u00168, "system__fat_lfltS");
   u00169 : constant Version_32 := 16#48f9793a#;
   pragma Export (C, u00169, "ma_lexicoB");
   u00170 : constant Version_32 := 16#a39e02a9#;
   pragma Export (C, u00170, "ma_lexicoS");
   u00171 : constant Version_32 := 16#8ec51ba6#;
   pragma Export (C, u00171, "ma_dictB");
   u00172 : constant Version_32 := 16#b0f120d1#;
   pragma Export (C, u00172, "ma_dictS");
   u00173 : constant Version_32 := 16#6bc02dbb#;
   pragma Export (C, u00173, "ma_token_ioB");
   u00174 : constant Version_32 := 16#ad4475e8#;
   pragma Export (C, u00174, "ma_token_ioS");
   u00175 : constant Version_32 := 16#f08789ae#;
   pragma Export (C, u00175, "ada__text_io__enumeration_auxB");
   u00176 : constant Version_32 := 16#52f1e0af#;
   pragma Export (C, u00176, "ada__text_io__enumeration_auxS");
   u00177 : constant Version_32 := 16#eb6581d5#;
   pragma Export (C, u00177, "ma_syntax_tokensS");
   u00178 : constant Version_32 := 16#4b37b589#;
   pragma Export (C, u00178, "system__val_enumB");
   u00179 : constant Version_32 := 16#d83c821f#;
   pragma Export (C, u00179, "system__val_enumS");
   u00180 : constant Version_32 := 16#afdd9afa#;
   pragma Export (C, u00180, "ma_lexico_dfaB");
   u00181 : constant Version_32 := 16#a47f2bc3#;
   pragma Export (C, u00181, "ma_lexico_dfaS");
   u00182 : constant Version_32 := 16#ce8c6475#;
   pragma Export (C, u00182, "ma_lexico_ioB");
   u00183 : constant Version_32 := 16#e7886c03#;
   pragma Export (C, u00183, "ma_lexico_ioS");
   u00184 : constant Version_32 := 16#2b70b149#;
   pragma Export (C, u00184, "system__concat_3B");
   u00185 : constant Version_32 := 16#68569c2f#;
   pragma Export (C, u00185, "system__concat_3S");
   u00186 : constant Version_32 := 16#819541f5#;
   pragma Export (C, u00186, "ma_syntaxB");
   u00187 : constant Version_32 := 16#c2446f96#;
   pragma Export (C, u00187, "ma_syntaxS");
   u00188 : constant Version_32 := 16#aca433ca#;
   pragma Export (C, u00188, "ma_syntax_gotoS");
   u00189 : constant Version_32 := 16#7ffda9a3#;
   pragma Export (C, u00189, "ma_syntax_shift_reduceS");
   u00190 : constant Version_32 := 16#932a4690#;
   pragma Export (C, u00190, "system__concat_4B");
   u00191 : constant Version_32 := 16#1d42ebaa#;
   pragma Export (C, u00191, "system__concat_4S");
   u00192 : constant Version_32 := 16#608e2cd1#;
   pragma Export (C, u00192, "system__concat_5B");
   u00193 : constant Version_32 := 16#e47883a4#;
   pragma Export (C, u00193, "system__concat_5S");
   u00194 : constant Version_32 := 16#fceb5070#;
   pragma Export (C, u00194, "lecture_entiersB");
   u00195 : constant Version_32 := 16#b02104db#;
   pragma Export (C, u00195, "lecture_entiersS");
   u00196 : constant Version_32 := 16#a3b0e77f#;
   pragma Export (C, u00196, "optionsB");
   u00197 : constant Version_32 := 16#d5b38fdb#;
   pragma Export (C, u00197, "optionsS");
   u00198 : constant Version_32 := 16#56eac0fd#;
   pragma Export (C, u00198, "partie_opB");
   u00199 : constant Version_32 := 16#5829a7d7#;
   pragma Export (C, u00199, "partie_opS");
   u00200 : constant Version_32 := 16#c86e2b51#;
   pragma Export (C, u00200, "lecture_reelsB");
   u00201 : constant Version_32 := 16#fc31a9f6#;
   pragma Export (C, u00201, "lecture_reelsS");
   --  BEGIN ELABORATION ORDER
   --  ada%s
   --  ada.characters%s
   --  ada.characters.handling%s
   --  ada.characters.latin_1%s
   --  ada.command_line%s
   --  interfaces%s
   --  system%s
   --  system.address_operations%s
   --  system.address_operations%b
   --  system.case_util%s
   --  system.case_util%b
   --  system.exn_llf%s
   --  system.exn_llf%b
   --  system.float_control%s
   --  system.float_control%b
   --  system.htable%s
   --  system.img_bool%s
   --  system.img_bool%b
   --  system.img_enum_new%s
   --  system.img_enum_new%b
   --  system.img_int%s
   --  system.img_int%b
   --  system.img_lli%s
   --  system.img_lli%b
   --  system.img_real%s
   --  system.io%s
   --  system.io%b
   --  system.parameters%s
   --  system.parameters%b
   --  system.crtl%s
   --  interfaces.c_streams%s
   --  interfaces.c_streams%b
   --  system.powten_table%s
   --  system.standard_library%s
   --  system.exceptions_debug%s
   --  system.exceptions_debug%b
   --  system.storage_elements%s
   --  system.storage_elements%b
   --  system.stack_checking%s
   --  system.stack_checking%b
   --  system.string_hash%s
   --  system.string_hash%b
   --  system.htable%b
   --  system.strings%s
   --  system.strings%b
   --  system.os_lib%s
   --  system.traceback_entries%s
   --  system.traceback_entries%b
   --  ada.exceptions%s
   --  system.soft_links%s
   --  system.unsigned_types%s
   --  system.fat_lflt%s
   --  system.fat_llf%s
   --  system.img_biu%s
   --  system.img_biu%b
   --  system.img_llb%s
   --  system.img_llb%b
   --  system.img_llu%s
   --  system.img_llu%b
   --  system.img_llw%s
   --  system.img_llw%b
   --  system.img_uns%s
   --  system.img_uns%b
   --  system.img_real%b
   --  system.img_wiu%s
   --  system.img_wiu%b
   --  system.val_enum%s
   --  system.val_int%s
   --  system.val_lli%s
   --  system.val_llu%s
   --  system.val_real%s
   --  system.val_uns%s
   --  system.val_util%s
   --  system.val_util%b
   --  system.val_uns%b
   --  system.val_real%b
   --  system.val_llu%b
   --  system.val_lli%b
   --  system.val_int%b
   --  system.val_enum%b
   --  system.wch_con%s
   --  system.wch_con%b
   --  system.wch_cnv%s
   --  system.wch_jis%s
   --  system.wch_jis%b
   --  system.wch_cnv%b
   --  system.wch_stw%s
   --  system.wch_stw%b
   --  ada.exceptions.last_chance_handler%s
   --  ada.exceptions.last_chance_handler%b
   --  ada.exceptions.traceback%s
   --  system.address_image%s
   --  system.bit_ops%s
   --  system.bit_ops%b
   --  system.compare_array_unsigned_8%s
   --  system.compare_array_unsigned_8%b
   --  system.concat_2%s
   --  system.concat_2%b
   --  system.concat_3%s
   --  system.concat_3%b
   --  system.concat_4%s
   --  system.concat_4%b
   --  system.concat_5%s
   --  system.concat_5%b
   --  system.exception_table%s
   --  system.exception_table%b
   --  ada.io_exceptions%s
   --  ada.strings%s
   --  ada.strings.maps%s
   --  ada.strings.maps.constants%s
   --  ada.tags%s
   --  ada.streams%s
   --  ada.streams%b
   --  interfaces.c%s
   --  system.exceptions%s
   --  system.exceptions%b
   --  system.exceptions.machine%s
   --  system.file_control_block%s
   --  system.file_io%s
   --  system.finalization_root%s
   --  system.finalization_root%b
   --  ada.finalization%s
   --  system.storage_pools%s
   --  system.storage_pools%b
   --  system.finalization_masters%s
   --  system.memory%s
   --  system.memory%b
   --  system.standard_library%b
   --  system.pool_global%s
   --  system.pool_global%b
   --  system.secondary_stack%s
   --  system.finalization_masters%b
   --  system.file_io%b
   --  interfaces.c%b
   --  ada.tags%b
   --  ada.strings.maps%b
   --  system.soft_links%b
   --  system.os_lib%b
   --  ada.command_line%b
   --  ada.characters.handling%b
   --  system.secondary_stack%b
   --  system.address_image%b
   --  ada.exceptions.traceback%b
   --  system.traceback%s
   --  system.traceback%b
   --  system.traceback.symbolic%s
   --  system.traceback.symbolic%b
   --  ada.exceptions%b
   --  ada.text_io%s
   --  ada.text_io%b
   --  ada.text_io.enumeration_aux%s
   --  ada.text_io.float_aux%s
   --  ada.text_io.generic_aux%s
   --  ada.text_io.generic_aux%b
   --  ada.text_io.float_aux%b
   --  ada.text_io.enumeration_aux%b
   --  ada.text_io.integer_aux%s
   --  ada.text_io.integer_aux%b
   --  ada.integer_text_io%s
   --  ada.integer_text_io%b
   --  text_io%s
   --  ma_lexico_dfa%s
   --  ma_lexico_dfa%b
   --  ma_lexico_io%s
   --  ma_lexico_io%b
   --  ma_syntax_goto%s
   --  ma_syntax_shift_reduce%s
   --  options%s
   --  options%b
   --  types_base%s
   --  types_base%b
   --  entier_es%s
   --  entier_es%b
   --  lecture_entiers%s
   --  lecture_entiers%b
   --  lecture_reels%s
   --  mes_tables%s
   --  mes_tables%b
   --  pseudo_code%s
   --  assembleur%s
   --  ma_detiq%s
   --  ma_detiq%b
   --  ma_syntax_tokens%s
   --  ma_dict%s
   --  ma_lexico%s
   --  ma_syntax%s
   --  ma_syntax%b
   --  assembleur%b
   --  ma_token_io%s
   --  ma_token_io%b
   --  ma_dict%b
   --  partie_op%s
   --  ima%b
   --  reel_es%s
   --  reel_es%b
   --  partie_op%b
   --  lecture_reels%b
   --  pseudo_code.table%s
   --  pseudo_code.table%b
   --  pseudo_code%b
   --  ma_lexico%b
   --  END ELABORATION ORDER


end ada_main;
