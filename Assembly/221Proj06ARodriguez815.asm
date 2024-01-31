; csc 221-01
; project 6
; Fill an array of 50 values with random values, and find out how many are negative
; Antonio Rodriguez
; 10/22/20

INCLUDE Irvine32.inc
INCLUDE Macros.inc


.data
    array DWORD 50 DUP(-1)
.code
main PROC
    mov esi, OFFSET array
    mov ecx, LENGTHOF array  
    call FillArray
    call DisplayArray
    call CountArray
    call CRLF
    mwrite <"There was ">
    call WriteDec
    mwrite <" negative values.",13,10>
    exit
main ENDP

;  fill an array with random integers;
;  ESI: address of the array to fill 
;  ECX: number of element in array 
;   
;  uses IRVINE's RANDOM proceedure
;
FillArray PROC USES ECX ESI  
    call Randomize		            	; seed random number generator
    TOP:
		call random32			        ; invloke IRVINE's RANDOw32
		mov [esi], eax			        ; put new value in array 
		add esi, TYPE DWORD             ; increament address 
    LOOP TOP
    ret
FillArray ENDP

;  Description: Loop through the array, displaying each value, 
;  Recieves:    ESI: address of the array to look at 
;               ECX: number of element in array 
;  Returns:
;  Requires:    
DisplayArray PROC USES ECX ; YOU MAY NEED TO ADD MORE TO THIS USES CLAUSE HERE 
    mov EDX, 0  		                ; use as negitive_count (eax is used for WriteInt)
    TOP:
	    mov eax, [esi]			        ; move element into EAX
		add esi, TYPE DWORD			    ; increatemnt esi
    call WriteInt       			    ; write out value 
    mwriteln <" ">		            	; write aSPACE
	LOOP TOP					
	ret
DisplayArray ENDP

;  Description: Loop through the array countng the number of negative values;
;  Recieves:    ESI: address of the array to look at 
;               ECX: number of element in array 
;  Returns:     EAX: number of negative values 
;  Requires:    
CountArray PROC USES ECX ; YOU MAY NEED TO ADD MORE TO THIS USES CLAUSE HERE 
    mov EAX, 0                          ; use as negitive_count 
    TOP:
        mov eax, [esi]                  ; move element into EAX
        add esi, TYPE DWORD             ; increatemnt esi
        or EAX, EAX                     ; set CPU flags - this will will set Sign flag if eax is negative
        jns SKIP                        ; jmp over if NOT negative (Jump Not Signed)
            inc edx                     ; incraetent crement EDX (neg count)
        SKIP:
    LOOP TOP
    ret
CountArray ENDP

END main