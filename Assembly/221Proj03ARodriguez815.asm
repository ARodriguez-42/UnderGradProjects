; csc 221-01
; project 3
; Getting forst 6 numbers in the Fibonocci Sequence
; Antonio Rodriguez
; 10/09/20

INCLUDE Irvine32.inc

.data
   msg BYTE "The first six numbers in the Fibonacci Sequence are"

.code
   main PROC
      mov edx, OFFSET msg
      CALL WriteString
      CALL CRLF
      mov ebx, 0
      mov eax, ebx 
      CALL WriteInt
      CALL CRLF
      mov ecx, 1
      mov eax, ecx   ; ecx = 1
      CALL WriteInt
      CALL CRLF
      add ecx, ebx    ; 1 + 0
      mov eax, ecx
      CALL WriteInt
      CALL CRLF
      mov ebx, eax    ; bx = 1
      add ecx, ebx    ; 1 + 1 = ecx
      mov eax, ecx    ; 2 -> ax
      CALL WriteInt
      CALL CRLF
      mov edx, ecx    ; dx = 2
      add ecx, ebx    ; 2 + 1 = ecx
      mov eax, ecx     ; 3 -> ax
      CALL WriteInt
      CALL CRLF
      add ecx, edx
      mov eax, ecx
      CALL WriteInt
      exit
   main ENDP
   END main
