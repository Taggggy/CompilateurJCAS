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

   Ada_Main_Program_Name : constant String := "_ada_test_pseudo_code" & ASCII.NUL;
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
   u00001 : constant Version_32 := 16#870f75ca#;
   pragma Export (C, u00001, "test_pseudo_codeB");
   u00002 : constant Version_32 := 16#b6df930e#;
   pragma Export (C, u00002, "system__standard_libraryB");
   u00003 : constant Version_32 := 16#937076cc#;
   pragma Export (C, u00003, "system__standard_libraryS");
   u00004 : constant Version_32 := 16#3ffc8e18#;
   pragma Export (C, u00004, "adaS");
   u00005 : constant Version_32 := 16#c1a106e2#;
   pragma Export (C, u00005, "ada__text_ioB");
   u00006 : constant Version_32 := 16#8d734ca7#;
   pragma Export (C, u00006, "ada__text_ioS");
   u00007 : constant Version_32 := 16#e7214354#;
   pragma Export (C, u00007, "ada__exceptionsB");
   u00008 : constant Version_32 := 16#020f9e08#;
   pragma Export (C, u00008, "ada__exceptionsS");
   u00009 : constant Version_32 := 16#e947e6a9#;
   pragma Export (C, u00009, "ada__exceptions__last_chance_handlerB");
   u00010 : constant Version_32 := 16#41e5552e#;
   pragma Export (C, u00010, "ada__exceptions__last_chance_handlerS");
   u00011 : constant Version_32 := 16#6326c08a#;
   pragma Export (C, u00011, "systemS");
   u00012 : constant Version_32 := 16#465d427a#;
   pragma Export (C, u00012, "system__soft_linksB");
   u00013 : constant Version_32 := 16#fda218df#;
   pragma Export (C, u00013, "system__soft_linksS");
   u00014 : constant Version_32 := 16#b01dad17#;
   pragma Export (C, u00014, "system__parametersB");
   u00015 : constant Version_32 := 16#1d0ccdf5#;
   pragma Export (C, u00015, "system__parametersS");
   u00016 : constant Version_32 := 16#0f0cb66d#;
   pragma Export (C, u00016, "system__secondary_stackB");
   u00017 : constant Version_32 := 16#c8470fe3#;
   pragma Export (C, u00017, "system__secondary_stackS");
   u00018 : constant Version_32 := 16#39a03df9#;
   pragma Export (C, u00018, "system__storage_elementsB");
   u00019 : constant Version_32 := 16#4ee58a8e#;
   pragma Export (C, u00019, "system__storage_elementsS");
   u00020 : constant Version_32 := 16#41837d1e#;
   pragma Export (C, u00020, "system__stack_checkingB");
   u00021 : constant Version_32 := 16#ed99ab62#;
   pragma Export (C, u00021, "system__stack_checkingS");
   u00022 : constant Version_32 := 16#87a448ff#;
   pragma Export (C, u00022, "system__exception_tableB");
   u00023 : constant Version_32 := 16#3e88a9c8#;
   pragma Export (C, u00023, "system__exception_tableS");
   u00024 : constant Version_32 := 16#ce4af020#;
   pragma Export (C, u00024, "system__exceptionsB");
   u00025 : constant Version_32 := 16#0b45ad7c#;
   pragma Export (C, u00025, "system__exceptionsS");
   u00026 : constant Version_32 := 16#4c9e814d#;
   pragma Export (C, u00026, "system__exceptions__machineS");
   u00027 : constant Version_32 := 16#aa0563fc#;
   pragma Export (C, u00027, "system__exceptions_debugB");
   u00028 : constant Version_32 := 16#1dac394e#;
   pragma Export (C, u00028, "system__exceptions_debugS");
   u00029 : constant Version_32 := 16#570325c8#;
   pragma Export (C, u00029, "system__img_intB");
   u00030 : constant Version_32 := 16#61fd2048#;
   pragma Export (C, u00030, "system__img_intS");
   u00031 : constant Version_32 := 16#39df8c17#;
   pragma Export (C, u00031, "system__tracebackB");
   u00032 : constant Version_32 := 16#3d041e4e#;
   pragma Export (C, u00032, "system__tracebackS");
   u00033 : constant Version_32 := 16#9ed49525#;
   pragma Export (C, u00033, "system__traceback_entriesB");
   u00034 : constant Version_32 := 16#637d36fa#;
   pragma Export (C, u00034, "system__traceback_entriesS");
   u00035 : constant Version_32 := 16#6fd210f2#;
   pragma Export (C, u00035, "system__traceback__symbolicB");
   u00036 : constant Version_32 := 16#dd19f67a#;
   pragma Export (C, u00036, "system__traceback__symbolicS");
   u00037 : constant Version_32 := 16#701f9d88#;
   pragma Export (C, u00037, "ada__exceptions__tracebackB");
   u00038 : constant Version_32 := 16#20245e75#;
   pragma Export (C, u00038, "ada__exceptions__tracebackS");
   u00039 : constant Version_32 := 16#57a37a42#;
   pragma Export (C, u00039, "system__address_imageB");
   u00040 : constant Version_32 := 16#c2ca5db0#;
   pragma Export (C, u00040, "system__address_imageS");
   u00041 : constant Version_32 := 16#8c33a517#;
   pragma Export (C, u00041, "system__wch_conB");
   u00042 : constant Version_32 := 16#785be258#;
   pragma Export (C, u00042, "system__wch_conS");
   u00043 : constant Version_32 := 16#9721e840#;
   pragma Export (C, u00043, "system__wch_stwB");
   u00044 : constant Version_32 := 16#554ace59#;
   pragma Export (C, u00044, "system__wch_stwS");
   u00045 : constant Version_32 := 16#b96cfbef#;
   pragma Export (C, u00045, "system__wch_cnvB");
   u00046 : constant Version_32 := 16#77ec58ab#;
   pragma Export (C, u00046, "system__wch_cnvS");
   u00047 : constant Version_32 := 16#4be8ce1b#;
   pragma Export (C, u00047, "interfacesS");
   u00048 : constant Version_32 := 16#ece6fdb6#;
   pragma Export (C, u00048, "system__wch_jisB");
   u00049 : constant Version_32 := 16#f79c418a#;
   pragma Export (C, u00049, "system__wch_jisS");
   u00050 : constant Version_32 := 16#10558b11#;
   pragma Export (C, u00050, "ada__streamsB");
   u00051 : constant Version_32 := 16#2e6701ab#;
   pragma Export (C, u00051, "ada__streamsS");
   u00052 : constant Version_32 := 16#db5c917c#;
   pragma Export (C, u00052, "ada__io_exceptionsS");
   u00053 : constant Version_32 := 16#920eada5#;
   pragma Export (C, u00053, "ada__tagsB");
   u00054 : constant Version_32 := 16#13ca27f3#;
   pragma Export (C, u00054, "ada__tagsS");
   u00055 : constant Version_32 := 16#c3335bfd#;
   pragma Export (C, u00055, "system__htableB");
   u00056 : constant Version_32 := 16#e7e47360#;
   pragma Export (C, u00056, "system__htableS");
   u00057 : constant Version_32 := 16#089f5cd0#;
   pragma Export (C, u00057, "system__string_hashB");
   u00058 : constant Version_32 := 16#45ba181e#;
   pragma Export (C, u00058, "system__string_hashS");
   u00059 : constant Version_32 := 16#fe7e644a#;
   pragma Export (C, u00059, "system__unsigned_typesS");
   u00060 : constant Version_32 := 16#06052bd0#;
   pragma Export (C, u00060, "system__val_lluB");
   u00061 : constant Version_32 := 16#848c3338#;
   pragma Export (C, u00061, "system__val_lluS");
   u00062 : constant Version_32 := 16#27b600b2#;
   pragma Export (C, u00062, "system__val_utilB");
   u00063 : constant Version_32 := 16#cf867674#;
   pragma Export (C, u00063, "system__val_utilS");
   u00064 : constant Version_32 := 16#d1060688#;
   pragma Export (C, u00064, "system__case_utilB");
   u00065 : constant Version_32 := 16#472fa95d#;
   pragma Export (C, u00065, "system__case_utilS");
   u00066 : constant Version_32 := 16#84a27f0d#;
   pragma Export (C, u00066, "interfaces__c_streamsB");
   u00067 : constant Version_32 := 16#a06e9ee4#;
   pragma Export (C, u00067, "interfaces__c_streamsS");
   u00068 : constant Version_32 := 16#13b71684#;
   pragma Export (C, u00068, "system__crtlS");
   u00069 : constant Version_32 := 16#f1dc49a7#;
   pragma Export (C, u00069, "system__file_ioB");
   u00070 : constant Version_32 := 16#c45721ef#;
   pragma Export (C, u00070, "system__file_ioS");
   u00071 : constant Version_32 := 16#cf417de3#;
   pragma Export (C, u00071, "ada__finalizationS");
   u00072 : constant Version_32 := 16#95817ed8#;
   pragma Export (C, u00072, "system__finalization_rootB");
   u00073 : constant Version_32 := 16#2cd4b31a#;
   pragma Export (C, u00073, "system__finalization_rootS");
   u00074 : constant Version_32 := 16#769e25e6#;
   pragma Export (C, u00074, "interfaces__cB");
   u00075 : constant Version_32 := 16#61e3d2ff#;
   pragma Export (C, u00075, "interfaces__cS");
   u00076 : constant Version_32 := 16#b97322e0#;
   pragma Export (C, u00076, "system__os_libB");
   u00077 : constant Version_32 := 16#dc0cac3f#;
   pragma Export (C, u00077, "system__os_libS");
   u00078 : constant Version_32 := 16#1a817b8e#;
   pragma Export (C, u00078, "system__stringsB");
   u00079 : constant Version_32 := 16#1d99d1ec#;
   pragma Export (C, u00079, "system__stringsS");
   u00080 : constant Version_32 := 16#9eb95a22#;
   pragma Export (C, u00080, "system__file_control_blockS");
   u00081 : constant Version_32 := 16#956644e6#;
   pragma Export (C, u00081, "pseudo_codeB");
   u00082 : constant Version_32 := 16#6837ff4f#;
   pragma Export (C, u00082, "pseudo_codeS");
   u00083 : constant Version_32 := 16#1384aef2#;
   pragma Export (C, u00083, "entier_esB");
   u00084 : constant Version_32 := 16#6f99cae8#;
   pragma Export (C, u00084, "entier_esS");
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
   u00105 : constant Version_32 := 16#1c33b50d#;
   pragma Export (C, u00105, "types_baseB");
   u00106 : constant Version_32 := 16#447091ce#;
   pragma Export (C, u00106, "types_baseS");
   u00107 : constant Version_32 := 16#5182b687#;
   pragma Export (C, u00107, "pseudo_code__tableB");
   u00108 : constant Version_32 := 16#6cd79f5c#;
   pragma Export (C, u00108, "pseudo_code__tableS");
   u00109 : constant Version_32 := 16#12c24a43#;
   pragma Export (C, u00109, "ada__charactersS");
   u00110 : constant Version_32 := 16#8f637df8#;
   pragma Export (C, u00110, "ada__characters__handlingB");
   u00111 : constant Version_32 := 16#3b3f6154#;
   pragma Export (C, u00111, "ada__characters__handlingS");
   u00112 : constant Version_32 := 16#4b7bb96a#;
   pragma Export (C, u00112, "ada__characters__latin_1S");
   u00113 : constant Version_32 := 16#af50e98f#;
   pragma Export (C, u00113, "ada__stringsS");
   u00114 : constant Version_32 := 16#e2ea8656#;
   pragma Export (C, u00114, "ada__strings__mapsB");
   u00115 : constant Version_32 := 16#1e526bec#;
   pragma Export (C, u00115, "ada__strings__mapsS");
   u00116 : constant Version_32 := 16#ad32e455#;
   pragma Export (C, u00116, "system__bit_opsB");
   u00117 : constant Version_32 := 16#0765e3a3#;
   pragma Export (C, u00117, "system__bit_opsS");
   u00118 : constant Version_32 := 16#92f05f13#;
   pragma Export (C, u00118, "ada__strings__maps__constantsS");
   u00119 : constant Version_32 := 16#5b9edcc4#;
   pragma Export (C, u00119, "system__compare_array_unsigned_8B");
   u00120 : constant Version_32 := 16#ca25b107#;
   pragma Export (C, u00120, "system__compare_array_unsigned_8S");
   u00121 : constant Version_32 := 16#5f72f755#;
   pragma Export (C, u00121, "system__address_operationsB");
   u00122 : constant Version_32 := 16#702a7eb9#;
   pragma Export (C, u00122, "system__address_operationsS");
   u00123 : constant Version_32 := 16#fd83e873#;
   pragma Export (C, u00123, "system__concat_2B");
   u00124 : constant Version_32 := 16#6186175a#;
   pragma Export (C, u00124, "system__concat_2S");
   u00125 : constant Version_32 := 16#d0432c8d#;
   pragma Export (C, u00125, "system__img_enum_newB");
   u00126 : constant Version_32 := 16#026ac64a#;
   pragma Export (C, u00126, "system__img_enum_newS");
   u00127 : constant Version_32 := 16#6abe5dbe#;
   pragma Export (C, u00127, "system__finalization_mastersB");
   u00128 : constant Version_32 := 16#38daf940#;
   pragma Export (C, u00128, "system__finalization_mastersS");
   u00129 : constant Version_32 := 16#7268f812#;
   pragma Export (C, u00129, "system__img_boolB");
   u00130 : constant Version_32 := 16#96ffb161#;
   pragma Export (C, u00130, "system__img_boolS");
   u00131 : constant Version_32 := 16#d7aac20c#;
   pragma Export (C, u00131, "system__ioB");
   u00132 : constant Version_32 := 16#fd6437c5#;
   pragma Export (C, u00132, "system__ioS");
   u00133 : constant Version_32 := 16#6d4d969a#;
   pragma Export (C, u00133, "system__storage_poolsB");
   u00134 : constant Version_32 := 16#40cb5e27#;
   pragma Export (C, u00134, "system__storage_poolsS");
   u00135 : constant Version_32 := 16#5a895de2#;
   pragma Export (C, u00135, "system__pool_globalB");
   u00136 : constant Version_32 := 16#7141203e#;
   pragma Export (C, u00136, "system__pool_globalS");
   u00137 : constant Version_32 := 16#58e7cff7#;
   pragma Export (C, u00137, "system__memoryB");
   u00138 : constant Version_32 := 16#3a5ba6be#;
   pragma Export (C, u00138, "system__memoryS");
   u00139 : constant Version_32 := 16#c7fe82f1#;
   pragma Export (C, u00139, "reel_esB");
   u00140 : constant Version_32 := 16#bbe3e6eb#;
   pragma Export (C, u00140, "reel_esS");
   u00141 : constant Version_32 := 16#d5f9759f#;
   pragma Export (C, u00141, "ada__text_io__float_auxB");
   u00142 : constant Version_32 := 16#f854caf5#;
   pragma Export (C, u00142, "ada__text_io__float_auxS");
   u00143 : constant Version_32 := 16#237a28d3#;
   pragma Export (C, u00143, "system__img_realB");
   u00144 : constant Version_32 := 16#a48e9168#;
   pragma Export (C, u00144, "system__img_realS");
   u00145 : constant Version_32 := 16#67b17b79#;
   pragma Export (C, u00145, "system__fat_llfS");
   u00146 : constant Version_32 := 16#1b28662b#;
   pragma Export (C, u00146, "system__float_controlB");
   u00147 : constant Version_32 := 16#83da83b6#;
   pragma Export (C, u00147, "system__float_controlS");
   u00148 : constant Version_32 := 16#f1f88835#;
   pragma Export (C, u00148, "system__img_lluB");
   u00149 : constant Version_32 := 16#b7b76489#;
   pragma Export (C, u00149, "system__img_lluS");
   u00150 : constant Version_32 := 16#eef535cd#;
   pragma Export (C, u00150, "system__img_unsB");
   u00151 : constant Version_32 := 16#618a58bd#;
   pragma Export (C, u00151, "system__img_unsS");
   u00152 : constant Version_32 := 16#3356a6fd#;
   pragma Export (C, u00152, "system__powten_tableS");
   u00153 : constant Version_32 := 16#faa9a7b2#;
   pragma Export (C, u00153, "system__val_realB");
   u00154 : constant Version_32 := 16#9d0fb79b#;
   pragma Export (C, u00154, "system__val_realS");
   u00155 : constant Version_32 := 16#6c05c057#;
   pragma Export (C, u00155, "system__exn_llfB");
   u00156 : constant Version_32 := 16#df587b56#;
   pragma Export (C, u00156, "system__exn_llfS");
   u00157 : constant Version_32 := 16#1d61d593#;
   pragma Export (C, u00157, "system__fat_lfltS");
   --  BEGIN ELABORATION ORDER
   --  ada%s
   --  ada.characters%s
   --  ada.characters.handling%s
   --  ada.characters.latin_1%s
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
   --  ada.text_io.float_aux%s
   --  ada.text_io.generic_aux%s
   --  ada.text_io.generic_aux%b
   --  ada.text_io.float_aux%b
   --  ada.text_io.integer_aux%s
   --  ada.text_io.integer_aux%b
   --  types_base%s
   --  types_base%b
   --  entier_es%s
   --  entier_es%b
   --  pseudo_code%s
   --  test_pseudo_code%b
   --  reel_es%s
   --  reel_es%b
   --  pseudo_code.table%s
   --  pseudo_code.table%b
   --  pseudo_code%b
   --  END ELABORATION ORDER


end ada_main;
